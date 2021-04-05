package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Dialog;
import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.respos.DialogsRepository;
import com.bonnysid.bloom.respos.MessageRepository;
import com.bonnysid.bloom.security.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final DialogsRepository dialogsRepository;
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public ChatService(DialogsRepository dialogsRepository, MessageRepository messageRepository, UserService userService) {
        this.dialogsRepository = dialogsRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public void sendMessage(Long idTo, Message message) {
        if (userService.getUser(idTo) != null) {
            messageRepository.save(message);
        }
    }

    public List<Message> getMessagesByDialogId(Long id) {
        if(!checkDialogRequest(id)) throw new JwtAuthenticationException("You don't have access to this dialog!");
        return messageRepository.getMessagesByDialogId(id).orElseThrow(() -> new IllegalStateException("Dialog with this id doesn't exists!"));
    }

    public boolean checkDialogRequest(Long id) {
        return userService.getAuthId().equals(dialogsRepository.findById(id).orElseThrow(() -> new IllegalStateException("Dialog doesn't exists!")).getIdFromUser());
    }

    public void createDialog(Long idTo) {
        if(dialogsRepository.findByFromIDAndToID(userService.getAuthId(), idTo).isPresent()) throw new IllegalStateException("This dialog already exists");
        Dialog dialog = new Dialog(userService.getAuthId(), idTo);
        dialogsRepository.save(dialog);
    }
}
