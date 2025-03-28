package com.server.cocoapp.dto;

import org.springframework.beans.factory.annotation.Value;

import com.server.cocoapp.classes.PetStatus;
import com.server.cocoapp.entities.Pet;

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

    @Value("${base.url}")
    private String imageUrl;

    private String ownerId;

    private float northCoordinate;

    private float eastCoordinate;

    private String address;

    public void update(Pet pet) {
        if (pet.getId() != null) this.id = pet.getId();
        if (pet.getPetName() != null) this.petName = pet.getPetName();
        if (pet.getBreedName() != null) this.breedName = pet.getBreedName();
        if (pet.getColor() != null) this.color = pet.getColor();
        if (pet.getGender() != null) this.gender = pet.getGender();
        if (pet.getHeight() != 0) this.height = pet.getHeight();
        if (pet.getStatus() != null) this.status = pet.getStatus();
        if (pet.getWeight() != 0) this.weight = pet.getWeight();
        if (pet.getOwnerId() != null) this.ownerId = pet.getOwnerId();
        if (pet.getAge() != 0) this.age = pet.getAge();
        if (pet.getNorthCoordinate() != 0) this.northCoordinate = pet.getNorthCoordinate();
        if (pet.getEastCoordinate() != 0) this.eastCoordinate = pet.getEastCoordinate();

        if (pet.getAddress() != null) address = pet.getAddress();

        if (pet.getImageName() != null) {
            imageUrl = "/file/" + pet.getImageName(); 
        }
    }
}
