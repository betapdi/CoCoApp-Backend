package com.server.cocoapp.exceptions;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message == null ? "User not found!" : message);
    }
}
