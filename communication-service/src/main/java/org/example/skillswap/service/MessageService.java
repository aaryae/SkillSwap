package org.example.skillswap.service;

import lombok.RequiredArgsConstructor;
import org.example.skillswap.dto.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    public void publish(ChatMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
