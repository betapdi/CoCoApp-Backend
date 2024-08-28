package com.springboot.test.movieApi.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.test.movieApi.entities.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByName(String name); 
}