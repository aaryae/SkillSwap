package org.example.commonlibrary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final String message;
    private final boolean success;
    private final T data;

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