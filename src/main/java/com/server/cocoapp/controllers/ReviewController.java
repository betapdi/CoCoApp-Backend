package com.server.cocoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.cocoapp.dto.ReviewDto;
import com.server.cocoapp.services.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> response = reviewService.getAllReviews();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @PostMapping("/add")
    public ResponseEntity<ReviewDto> addReview(@AuthenticationPrincipal UserDetails userDetails, @RequestPart ReviewDto reviewDto) {
        ReviewDto response = reviewService.addReview(userDetails.getUsername(), reviewDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("reviewId") String reviewId) {
        String response = reviewService.deleteReview(userDetails.getUsername(), reviewId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ReviewDto> updateReview(@AuthenticationPrincipal UserDetails userDetails, @RequestPart ReviewDto reviewDto) {
        ReviewDto response = reviewService.updateReview(userDetails.getUsername(), reviewDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
}
