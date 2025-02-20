package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.classes.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
}