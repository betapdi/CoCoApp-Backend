package com.springboot.test.movieApi.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.springboot.test.movieApi.entities.Movie;
import com.springboot.test.movieApi.repositories.MovieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
