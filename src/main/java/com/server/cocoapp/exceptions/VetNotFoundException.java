package com.server.cocoapp.exceptions;

public class VetNotFoundException extends RuntimeException {
    
    public VetNotFoundException(String message) {
        super(message == null ? "Vet not found!" : message);
    }
}
