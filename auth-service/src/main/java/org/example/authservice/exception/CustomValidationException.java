package org.example.authservice.exception;

import jakarta.validation.ValidationException;

public class CustomValidationException extends ValidationException {
    public CustomValidationException(String message) {
        super(message);
    }
}
