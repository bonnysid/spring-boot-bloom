package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.model.view.DialogView;
import com.bonnysid.bloom.model.view.MessageView;
import com.bonnysid.bloom.services.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PreAuthorize("hasAuthority('user:read')")
    @MessageMapping("chat/{id}")
    public void sendMessage(@DestinationVariable Long idTo, Message message) {
        System.out.println("Handling sended message: " + message + "\n to: " + idTo);
        chatService.sendMessage(idTo, message);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("dialogs/{id}/messages")
    public List<MessageView> getMessages(@PathVariable Long id) {
        return chatService.getMessagesByUserId(id);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @PostMapping("dialogs/{id}")
    public void createDialog(@PathVariable Long id) {
        chatService.createDialog(id);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @DeleteMapping("dialogs/{id}")
    public void deleteDialog(@PathVariable Long id) {
        chatService.deleteDialog(id);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("dialogs")
    public List<DialogView> getDialogs() {
        return chatService.getDialogs();
    }
}
