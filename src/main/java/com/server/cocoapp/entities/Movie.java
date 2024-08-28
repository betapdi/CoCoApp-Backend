package com.server.cocoapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Movie {
    @Id
    @Indexed
    private String id;
    private String name;
    private String description;
    private String director;
    private List<String> actors;
    private LocalDateTime created;

    public Movie(String name, String description, String director, List<String> actors,
            LocalDateTime created) {
        this.name = name;
        this.description = description;
        this.director = director;
        this.actors = actors;
        this.created = created;
    }
}

