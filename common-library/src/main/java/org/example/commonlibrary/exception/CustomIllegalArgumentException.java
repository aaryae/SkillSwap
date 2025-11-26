package org.example.commonlibrary.exception;

public class CustomIllegalArgumentException extends IllegalArgumentException{

    public CustomIllegalArgumentException(String message) {
        super(message);
    }
    public CustomIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }



}
