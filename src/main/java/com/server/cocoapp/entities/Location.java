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
    private Integer startDate = 2;

    @Builder.Default
    private Integer endDate = 8;

    @Builder.Default 
    private float startTime = 0;

    @Builder.Default
    private float endTime = 23;

    public void update(LocationDto dto) {
        if (dto.getEndDate() != 0) endDate = dto.getEndDate();
        if (dto.getEndTime() != 0) endTime = dto.getEndTime();
        if (dto.getName() != null) name = dto.getName();
        if (dto.getPrice() != 0) price = dto.getPrice();
        if (dto.getStartDate() != 0) startDate = dto.getStartDate();
        if (dto.getStartTime() != 0) startTime = dto.getStartTime();
    }
}
