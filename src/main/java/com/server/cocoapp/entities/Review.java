package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.dto.ReviewDto;

import lombok.NonNull;
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
public class Review {
    @Id
    String id;

    @NonNull
    String locationId;
    
    String detail;

    Integer rating;

    public void update(ReviewDto dto) {
        if (dto.getDetail() != null) detail = dto.getDetail();
        if (dto.getLocationId() != null) detail = dto.getLocationId();
        if (dto.getRating() != 0) rating = dto.getRating();
    }
}
