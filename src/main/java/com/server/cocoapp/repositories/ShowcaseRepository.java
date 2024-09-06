package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Showcase;

public interface ShowcaseRepository extends MongoRepository<Showcase, String> {
}