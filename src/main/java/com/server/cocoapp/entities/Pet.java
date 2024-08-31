package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pet {
    @Id
    @Indexed
    private String id;

    @NonNull
    private String petName;

    private String breedName;

    private Character gender;

    private int age;

    private String color;

    private float height;
    
    private float weight;

    private byte[] image;
}
