package com.server.cocoapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.cocoapp.entities.Movie;
import com.server.cocoapp.services.MovieService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Movie> fetchAllMovies() {
        return movieService.getAllMovies();
    }
}
