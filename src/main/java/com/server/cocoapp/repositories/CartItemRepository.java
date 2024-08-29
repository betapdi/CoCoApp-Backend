package com.server.cocoapp.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
}