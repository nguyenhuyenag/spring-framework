package com.ai.controller;

import com.ai.ChatRequest;
import com.ai.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest message) {
        return chatService.chat(message);
    }

    @PostMapping("/chat-with-image")
    String chatWithImage(@RequestParam("file") MultipartFile file,
                         @RequestParam("message") String message) {
        return chatService.chatWithImage(file, message);
    }

}
