package com.server.cocoapp.exceptions;

public class ShopItemNotFoundException extends RuntimeException {
    
    public ShopItemNotFoundException(String message) {
        super(message);
    }
}
