package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.Dialog;
import com.bonnysid.bloom.model.Message;
import com.bonnysid.bloom.model.view.DialogView;
import com.bonnysid.bloom.model.view.MessageView;
import com.bonnysid.bloom.services.ChatService;
import org.springframework.http.ResponseEntity;
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

//    @PreAuthorize("hasAuthority('USER')")
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message) {
        Long idTo = Long.parseLong(to);
        System.out.println("Handling send message: " + message + "\n to: " + idTo);
        chatService.sendMessage(idTo, message);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("dialogs/{id}/messages")
    public List<MessageView> getMessages(@PathVariable Long id) {
        return chatService.getMessagesByDialogId(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("dialogs/{id}")
    public Dialog createDialog(@PathVariable Long id) {
        return chatService.createDialog(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("dialogs/{id}")
    public void deleteDialog(@PathVariable Long id) {
        chatService.deleteDialog(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("dialogs")
    public List<DialogView> getDialogs() {
        return chatService.getDialogs();
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/message/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return chatService.deleteMessage(id);
    }
}
