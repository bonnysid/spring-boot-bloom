package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.respos.DialogsRepository;
import com.bonnysid.bloom.respos.MessageRepository;
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
        return messageRepository.getMessagesByDialogId(id).orElseThrow(() -> new IllegalStateException("Dialog with this id doesn't exists!"));
    }

    public void createDialog() {

    }
}
