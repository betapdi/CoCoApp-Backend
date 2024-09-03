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

    private String workTime;

    private float rating;
    private float votes;
    private float distance;

    private String type; //"Grooming" or "Boarding" (if it's veterinarian, it's null)

    List<ReviewDto> reviews;

    @Value("${base.url}")
    String imageUrl;

    public void update(Location loc) {
        if (loc.getName() != null) name = loc.getName();
        if (loc.getPrice() != 0) price = loc.getPrice();
        if (loc.getWorkTime() != null) workTime = loc.getWorkTime();
        if (loc.getId() != null) id = loc.getId();
        if (loc.getDistance() != 0) distance = loc.getDistance();
        if (loc.getType() != null) type = loc.getType();
        
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
