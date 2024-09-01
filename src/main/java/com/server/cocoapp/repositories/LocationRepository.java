package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
}