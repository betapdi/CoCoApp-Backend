package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.classes.PetStatus;
import com.server.cocoapp.dto.PetDto;

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

    private PetStatus status;
 
    private String ownerId;

    private String imageName;

    private float northCoordinate;

    private float eastCoordinate;

    private String address;

    public void update(PetDto petDto) {
        if (petDto.getPetName() != null) this.petName = petDto.getPetName();
        if (petDto.getBreedName() != null) this.breedName = petDto.getBreedName();
        if (petDto.getColor() != null) this.color = petDto.getColor();
        if (petDto.getGender() != null) this.gender = petDto.getGender();
        if (petDto.getHeight() != 0) this.height = petDto.getHeight();
        if (petDto.getStatus() != null) this.status = petDto.getStatus();
        if (petDto.getWeight() != 0) this.weight = petDto.getWeight();
        if (petDto.getOwnerId() != null) this.ownerId = petDto.getOwnerId();
        if (petDto.getAge() != 0) this.age = petDto.getAge();
        if (petDto.getNorthCoordinate() != 0) this.northCoordinate = petDto.getNorthCoordinate();
        if (petDto.getEastCoordinate() != 0) this.eastCoordinate = petDto.getEastCoordinate();
        if (petDto.getAddress() != null) address = petDto.getAddress();
    }
}
