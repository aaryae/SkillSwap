package org.example.skillswap.dto;

public record ChatMessage(
        String from,
        String to,
        String content
) {}
