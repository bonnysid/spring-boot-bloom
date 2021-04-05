package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.model.view.MessageView;
import com.bonnysid.bloom.services.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/")
public class ChatController {

    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("chat/{id}")
    public void sendMessage(@DestinationVariable Long idTo, Message message) {
        System.out.println("Handling sended message: " + message + "\n to: " + idTo);
        chatService.sendMessage(idTo, message);
    }

    @GetMapping("dialogs/{id}/messages")
    public List<MessageView> getMessages(@PathVariable Long id) {
        return chatService.getMessagesByDialogId(id);
    }
}