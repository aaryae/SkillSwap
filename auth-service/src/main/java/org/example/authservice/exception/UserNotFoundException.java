package org.example.authservice.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

public class UserNotFoundException extends UsernameNotFoundException {
    private final String resourceName;
    private final String fieldName;
    private final Serializable fieldValue;

    public UserNotFoundException(String resourceName, String fieldName, Serializable fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
