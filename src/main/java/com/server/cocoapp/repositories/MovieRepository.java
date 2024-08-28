package com.server.cocoapp.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByName(String name); 
}