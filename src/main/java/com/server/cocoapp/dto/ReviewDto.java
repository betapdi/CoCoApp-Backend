package com.server.cocoapp.dto;

import com.mongodb.lang.NonNull;
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

    @NonNull
    String type;

    @NonNull
    String targetId;

    String detail;
    Integer rating;

    public void update(Review rev) {
        if (rev.getId() != null) id = rev.getId();
        if (rev.getDetail() != null) detail = rev.getDetail();
        if (rev.getTargetId() != null) targetId = rev.getTargetId();
        if (rev.getType() != null) type = rev.getType();
        if (rev.getRating() != 0) rating = rev.getRating();
    }
}
