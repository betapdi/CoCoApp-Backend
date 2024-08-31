package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {
}