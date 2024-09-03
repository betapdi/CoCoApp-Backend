package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Vet;

public interface VetRepository extends MongoRepository<Vet, String> {
}