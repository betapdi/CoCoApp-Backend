package com.server.cocoapp.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Pet;

public interface PetRepository extends MongoRepository<Pet, String> {
}