package org.example.skillswap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.skillswap.dto.ChatMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(@NonNull Message msg, byte[] pattern) {

        try {
            // Convert JSON bytes → ChatMessage object
            ChatMessage message = objectMapper.readValue(msg.getBody(), ChatMessage.class);

            // Push notification to the correct user
            messagingTemplate.convertAndSendToUser(
                    message.to(),
                    "/queue/messages",
                    message
            );

        } catch (Exception e) {
            System.err.println("Failed to deserialize Redis message: " + e.getMessage());
        }
    }
}
