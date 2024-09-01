package com.server.cocoapp.dto;

import org.springframework.data.annotation.Id;

import com.server.cocoapp.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    String id;
    String locationId;
    String detail;
    Integer rating;

    void update(Review rev) {
        if (rev.getId() != null) id = rev.getId();
        if (rev.getDetail() != null) detail = rev.getDetail();
        if (rev.getLocationId() != null) detail = rev.getLocationId();
        if (rev.getRating() != 0) rating = rev.getRating();
    }
}
