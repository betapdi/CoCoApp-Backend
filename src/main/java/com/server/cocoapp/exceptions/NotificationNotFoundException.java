package com.server.cocoapp.exceptions;

public class NotificationNotFoundException extends RuntimeException {
    
    public NotificationNotFoundException(String message) {
        super(message == null ? "Notification not found!" : message);
    }
}
