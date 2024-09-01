package com.server.cocoapp.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    
    public ReviewNotFoundException(String message) {
        super(message == null ? "Review not found!" : message);
    }
}
