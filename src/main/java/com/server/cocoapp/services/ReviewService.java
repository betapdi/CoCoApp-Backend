package com.server.cocoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.dto.ReviewDto;
import com.server.cocoapp.entities.Location;
import com.server.cocoapp.entities.Review;
import com.server.cocoapp.exceptions.LocationNotFoundException;
import com.server.cocoapp.exceptions.ReviewNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.LocationRepository;
import com.server.cocoapp.repositories.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> dtos = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto dto = new ReviewDto();
            dto.update(review);

            dtos.add(dto);
        }

        return dtos;
    }

    public ReviewDto getReview(String reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review not found!"));
        
        ReviewDto dto = new ReviewDto();
        dto.update(review);

        return dto;
    }

    public ReviewDto addReview(String username, ReviewDto reviewDto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Location location = locationRepository.findById(reviewDto.getLocationId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));

        Review review = new Review();
        review.update(reviewDto);

        user.getReviews().add(review);
        location.getReviews().add(review);

        userRepository.save(user);
        locationRepository.save(location);
        reviewRepository.save(review);

        ReviewDto dto = new ReviewDto();
        dto.update(review);

        return dto;
    }

    public String deleteReview(String username, String reviewId) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review not found!"));
        Location location = locationRepository.findById(review.getLocationId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));

        user.getReviews().removeIf(rev -> rev.getId().equals(reviewId));
        location.getReviews().removeIf(rev -> rev.getId().equals(reviewId));

        userRepository.save(user);
        locationRepository.save(location);
        reviewRepository.deleteById(reviewId);

        return "Review deleted with id: " + reviewId;
    }

    public ReviewDto updateReview(String username, ReviewDto reviewDto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Review review = reviewRepository.findById(reviewDto.getId()).orElseThrow(() -> new ReviewNotFoundException("Review not found!"));
        Location location = locationRepository.findById(review.getLocationId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));

        user.getReviews().removeIf(rev -> rev.getId().equals(reviewDto.getId()));
        location.getReviews().removeIf(rev -> rev.getId().equals(reviewDto.getId()));
        
        review.update(reviewDto);
        reviewRepository.save(review);

        user.getReviews().add(review);
        location.getReviews().add(review);

        userRepository.save(user);
        locationRepository.save(location);
        
        ReviewDto dto = new ReviewDto();
        dto.update(review);

        return dto;
    }
}
