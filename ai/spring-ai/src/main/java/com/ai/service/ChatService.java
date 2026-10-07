package com.ai.service;

import com.ai.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String chat(ChatRequest message) {
        System.out.println("message = " + message);
        // Customize
        SystemMessage systemMessage = new SystemMessage("""
                You are DOT-AI. You should response with a formal voice.
                """);
        UserMessage userMessage = new UserMessage(message.getMessage());
        Prompt prompt = new Prompt(systemMessage, userMessage);
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    public String chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are DOT-AI")
                .user(promptUserSpec
                        -> promptUserSpec.media(media) // build media từ hình ảnh
                        .text(message)) // text message
                .call()
                .content();
    }
}
