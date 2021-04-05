package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.services.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/api/1.0/chat/{id}")
    public void sendMessage(@DestinationVariable Long idTo, Message message) {
        System.out.println("Handling sended message: " + message + "\n to: " + idTo);
        chatService.sendMessage(idTo, message);
    }
}
