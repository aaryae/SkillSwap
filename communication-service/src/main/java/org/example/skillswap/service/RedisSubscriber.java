package org.example.skillswap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.dto.ChatMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(@NonNull Message msg, byte[] pattern) {

        try {
            ChatMessage message = objectMapper.readValue(msg.getBody(), ChatMessage.class);

            messagingTemplate.convertAndSendToUser(
                    message.to(),
                    "/queue/messages",
                    message
            );

        } catch (Exception e) {
            log.error("Failed to deserialize Redis message: {}", e.getMessage());
        }
    }
}
