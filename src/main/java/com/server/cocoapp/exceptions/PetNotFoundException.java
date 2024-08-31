package com.server.cocoapp.exceptions;

public class PetNotFoundException extends RuntimeException {
    
    public PetNotFoundException(String message) {
        super(message == null ? "Pet not found!" : message);
    }
}
