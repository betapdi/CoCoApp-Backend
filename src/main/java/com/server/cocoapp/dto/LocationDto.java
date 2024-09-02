package com.server.cocoapp.dto;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.server.cocoapp.entities.Location;
import com.server.cocoapp.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDto {
    private String id;
    private String name;
    private float price;
    private Integer startDate;
    private Integer endDate;
    private float startTime;
    private float endTime;

    private float rating;
    private float votes;

    List<ReviewDto> reviews;

    @Value("${base.url}")
    String imageUrl;

    public void update(Location loc) {
        if (loc.getEndDate() != 0) endDate = loc.getEndDate();
        if (loc.getEndTime() != 0) endTime = loc.getEndTime();
        if (loc.getName() != null) name = loc.getName();
        if (loc.getPrice() != 0) price = loc.getPrice();
        if (loc.getStartDate() != 0) startDate = loc.getStartDate();
        if (loc.getStartTime() != 0) startTime = loc.getStartTime();

        if (loc.getId() != null) id = loc.getId();
        
        if (loc.getReviews() != null) {
            votes = loc.getReviews().size();
            float sumRating = 0;
            reviews = new ArrayList<>();

            for (Review review : loc.getReviews()) {
                sumRating += review.getRating();

                ReviewDto dto = new ReviewDto();
                dto.update(review);
                reviews.add(dto);
            }

            rating = sumRating / votes;
        }

        if (loc.getImageName() != null) {
            imageUrl += "/file/" + loc.getImageName(); 
        }
    }
}
