package com.server.cocoapp.exceptions;

public class ShowcaseNotFoundException extends RuntimeException {
    
    public ShowcaseNotFoundException(String message) {
        super(message == null ? "Showcase not found!" : message);
    }
}
