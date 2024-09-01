package com.server.cocoapp.dto;

import com.server.cocoapp.classes.PetStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetDto {
    private String id;
    private String petName;
    private String breedName;
    private Character gender;
    private int age;
    private String color;
    private float height;
    private float weight;
    private PetStatus status;
    private String imageUrl;

    private String ownerId;
}
