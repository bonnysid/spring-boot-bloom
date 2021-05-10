package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Dialog;
import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.model.view.DialogView;
import com.bonnysid.bloom.model.view.MessageView;
import com.bonnysid.bloom.model.view.UserView;
import com.bonnysid.bloom.respos.DialogsRepository;
import com.bonnysid.bloom.respos.MessageRepository;
import com.bonnysid.bloom.security.AuthInfo;
import com.bonnysid.bloom.security.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final DialogsRepository dialogsRepository;
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AuthInfo authInfo;

    @Autowired
    public ChatService(DialogsRepository dialogsRepository, MessageRepository messageRepository, UserService userService, SimpMessagingTemplate simpMessagingTemplate, AuthInfo authInfo) {
        this.dialogsRepository = dialogsRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.authInfo = authInfo;
    }

    public void sendMessage(Long idTo, Message message) {
        if (userService.getUser(idTo) != null) {
            messageRepository.save(message);
            simpMessagingTemplate.convertAndSend("/topic/messages/" + idTo, message);
            if (dialogsRepository.findByFromIDAndToID(idTo, message.getIdFromUser()).isEmpty()) {
                Dialog dialog = dialogsRepository.save(new Dialog(idTo, message.getIdFromUser()));
                Message m = new Message();
                m.setDialogId(dialog.getId());
                m.setDate(message.getDate());
                m.setText(message.getText());
                m.setIdFromUser(message.getIdFromUser());
                messageRepository.save(m);
            }
        }
    }

    public List<MessageView> getMessagesByDialogId(Long id) {
        if (!checkDialogRequest(id)) throw new JwtAuthenticationException("You don't have access to this dialog!");
        return messageRepository.getMessagesByDialogId(id).orElseThrow(() -> new IllegalStateException("Dialog with this id doesn't exists!")).stream()
                .map(message -> new MessageView(message.getId(), message.getText(), userService.getUser(message.getIdFromUser()).getUsername(), message.getDate()))
                .collect(Collectors.toList());
    }

    public List<MessageView> getMessagesByUserId(Long id) {
        if (!checkDialogRequest(id)) throw new JwtAuthenticationException("Dialog doesn't exists!");
        return messageRepository.getMessagesByUserId(authInfo.getAuthId(), id).orElseThrow(() -> new IllegalStateException("Dialog with this id doesn't exists!")).stream()
                .map(message -> new MessageView(message.getId(), message.getText(), userService.getUser(message.getIdFromUser()).getUsername(), message.getDate()))
                .collect(Collectors.toList());
    }

    public boolean checkDialogRequest(Long id) {
        return dialogsRepository.findByFromIDAndToID(authInfo.getAuthId(), id).isPresent();
    }

    public void startChat(Long id) {
//        Dialog dialog = dialogsRepository.findByFromIDAndToID()
    }

    public Dialog createDialog(Long idTo) {
        if (dialogsRepository.findByFromIDAndToID(authInfo.getAuthId(), idTo).isPresent())
            throw new IllegalStateException("This dialog already exists");
        Dialog dialog = new Dialog(authInfo.getAuthId(), idTo);
        return dialog;
    }

    public void deleteDialog(Long id) {
        if (dialogsRepository.findById(id).isEmpty()) throw new IllegalStateException("This dialog already deleted");
        dialogsRepository.deleteById(id);
    }

    public List<DialogView> getDialogs() {
        return dialogsRepository
                .getAllByUserID(authInfo.getAuthId())
                .orElse(new ArrayList<>())
                .stream()
                .map(dialog -> {
                    UserView user = userService.getUser(dialog.getIdToUser());
                    Message lastMessage = dialog.getLastMessage();

                    return new DialogView(
                            dialog.getId(),
                            user.getUsername(),
                            user.getPhoto()
                                    .orElse(null),
                            lastMessage != null ? new MessageView(
                                    lastMessage.getId(),
                                    lastMessage.getText(),
                                    userService.getUser(lastMessage.getIdFromUser()).getUsername(),
                                    lastMessage.getDate()) : null);
                })
                .collect(Collectors.toList());
    }
}
