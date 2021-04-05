package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Dialog;
import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.respos.DialogsRepository;
import com.bonnysid.bloom.respos.MessageRepository;
import com.bonnysid.bloom.security.AuthInfo;
import com.bonnysid.bloom.security.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
        }
    }

    public List<Message> getMessagesByDialogId(Long id) {
        if(!checkDialogRequest(id)) throw new JwtAuthenticationException("You don't have access to this dialog!");
        return messageRepository.getMessagesByDialogId(id).orElseThrow(() -> new IllegalStateException("Dialog with this id doesn't exists!"));
    }

    public boolean checkDialogRequest(Long id) {
        return authInfo.getAuthId().equals(dialogsRepository.findById(id).orElseThrow(() -> new IllegalStateException("Dialog doesn't exists!")).getIdFromUser());
    }

    public void createDialog(Long idTo) {
        if(dialogsRepository.findByFromIDAndToID(authInfo.getAuthId(), idTo).isPresent()) throw new IllegalStateException("This dialog already exists");
        Dialog dialog = new Dialog(authInfo.getAuthId(), idTo);
        dialogsRepository.save(dialog);
    }
}
