package org.example.commonlibrary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(String message, boolean success, T data) {
    public ApiResponse(String message, boolean success) {
        this(message, success, null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, true, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(message, true, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, false, null);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(message, false, data);
    }
}