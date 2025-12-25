package org.example.authservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record RegisterRequest(@NotNull String email,@NotNull String password,@NotNull String username ) {}