package org.example.skillswap.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.skillswap.helper.StatusEnum;

public record UpdateRequest (@NotNull StatusEnum status){}
