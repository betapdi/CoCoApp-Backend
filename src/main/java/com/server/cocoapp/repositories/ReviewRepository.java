package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
}