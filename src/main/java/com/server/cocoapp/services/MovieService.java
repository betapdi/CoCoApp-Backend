package com.server.cocoapp.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.server.cocoapp.entities.Movie;
import com.server.cocoapp.repositories.MovieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
