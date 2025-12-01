package org.example.skillswap.controller;

import lombok.RequiredArgsConstructor;
import org.example.skillswap.dto.ChatMessage;
import org.example.skillswap.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final MessageService messageService;

    @MessageMapping("/chat.send")
    public void send(ChatMessage message) {
        messageService.publish(message); // publish to Redis
    }
}
