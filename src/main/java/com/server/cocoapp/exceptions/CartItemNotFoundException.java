package com.server.cocoapp.exceptions;

public class CartItemNotFoundException extends RuntimeException {
    
    public CartItemNotFoundException(String message) {
        super(message == null ? "CartItem not found!" : message);
    }
}
