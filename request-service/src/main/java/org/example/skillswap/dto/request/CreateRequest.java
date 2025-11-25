package org.example.skillswap.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRequest (@NotNull UUID skillId,@NotNull String message){}
