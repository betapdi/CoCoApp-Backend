package com.server.cocoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.dto.ReviewDto;
import com.server.cocoapp.entities.Location;
import com.server.cocoapp.entities.Review;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.exceptions.LocationNotFoundException;
import com.server.cocoapp.exceptions.ReviewNotFoundException;
import com.server.cocoapp.exceptions.ShopItemNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.LocationRepository;
import com.server.cocoapp.repositories.ReviewRepository;
import com.server.cocoapp.repositories.ShopItemRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ShopItemRepository shopItemRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, 
                        LocationRepository locationRepository, ShopItemRepository shopItemRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.shopItemRepository = shopItemRepository;
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
        
        Review review = new Review();
        review.update(reviewDto);
        
        user.getReviews().add(review);
        
        userRepository.save(user);
        reviewRepository.save(review);

        if (reviewDto.getType().equals("location")) {
            Location location = locationRepository.findById(reviewDto.getTargetId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
            location.getReviews().add(review);
            locationRepository.save(location);
        }

        else {
            ShopItem shopItem = shopItemRepository.findById(reviewDto.getTargetId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));
            shopItem.getReviews().add(review);
            shopItemRepository.save(shopItem);
        }


        ReviewDto dto = new ReviewDto();
        dto.update(review);

        return dto;
    }

    public String deleteReview(String username, String reviewId) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review not found!"));
        
        user.getReviews().removeIf(rev -> rev.getId().equals(reviewId));
        userRepository.save(user);
        reviewRepository.deleteById(reviewId);

        if (review.getType().equals("location")) {
            Location location = locationRepository.findById(review.getTargetId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
            location.getReviews().removeIf(rev -> rev.getId().equals(reviewId));
            locationRepository.save(location);
        }

        else {
            ShopItem shopItem = shopItemRepository.findById(review.getTargetId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));
            shopItem.getReviews().removeIf(rev -> rev.getId().equals(reviewId));
            shopItemRepository.save(shopItem);
        }


        return "Review deleted with id: " + reviewId;
    }

    public ReviewDto updateReview(String username, ReviewDto reviewDto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Review review = reviewRepository.findById(reviewDto.getId()).orElseThrow(() -> new ReviewNotFoundException("Review not found!"));
        
        //delete the old one
        user.getReviews().removeIf(rev -> rev.getId().equals(review.getId()));

        if (review.getType().equals("location")) {
            Location location = locationRepository.findById(review.getTargetId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
            location.getReviews().removeIf(rev -> rev.getId().equals(review.getId()));
            locationRepository.save(location);
        }

        else {
            ShopItem shopItem = shopItemRepository.findById(review.getTargetId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));
            shopItem.getReviews().removeIf(rev -> rev.getId().equals(review.getId()));
            shopItemRepository.save(shopItem);
        }

        //update
        review.update(reviewDto);

        //rewrite a new one
        user.getReviews().add(review);
        userRepository.save(user);

        if (review.getType().equals("location")) {
            Location location = locationRepository.findById(review.getTargetId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
            location.getReviews().add(review);
            locationRepository.save(location);
        }

        else {
            ShopItem shopItem = shopItemRepository.findById(review.getTargetId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));
            shopItem.getReviews().add(review);
            shopItemRepository.save(shopItem);
        }

        ReviewDto dto = new ReviewDto();
        dto.update(review);

        return dto;
    }
}
