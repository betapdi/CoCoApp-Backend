package com.server.cocoapp.exceptions;

public class LocationNotFoundException extends RuntimeException {
    
    public LocationNotFoundException(String message) {
        super(message == null ? "Location not found!" : message);
    }
}
