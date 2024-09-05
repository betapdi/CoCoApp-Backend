package com.server.cocoapp.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.dto.LocationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @Indexed
    private String id;

    private String name;

    private String imageName;
    
    @DBRef
    @Builder.Default
    List<Review> reviews = new ArrayList<>();

    @Builder.Default
    private float price = 100;

    @Builder.Default
    private String workTime = "Mon-Fri, 8AM-5PM";

    @Builder.Default
    private float distance = 5;

    private String type; //"Grooming" or "Boarding" (if it's veterinarian, it's null)

    private float northCoordinate;

    private float eastCoordinate;

    private String address;

    public void update(LocationDto dto) {
        if (dto.getWorkTime() != null) workTime = dto.getWorkTime();
        if (dto.getName() != null) name = dto.getName();
        if (dto.getPrice() != 0) price = dto.getPrice();
        if (dto.getDistance() != 0) distance = dto.getDistance();
        if (dto.getType() != null) type = dto.getType();
        if (dto.getEastCoordinate() != 0) eastCoordinate = dto.getEastCoordinate();
        if (dto.getNorthCoordinate() != 0) northCoordinate = dto.getNorthCoordinate();
        if (dto.getAddress() != null) address = dto.getAddress();
    }
}
