package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.CartHistory;

public interface CartHistoryRepository extends MongoRepository<CartHistory, String> {
}