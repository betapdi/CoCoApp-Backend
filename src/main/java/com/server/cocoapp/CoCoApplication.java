package com.server.cocoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.server.cocoapp.repositories.CartHistoryRepository;
import com.server.cocoapp.repositories.CartRepository;
import com.server.cocoapp.repositories.LocationRepository;
import com.server.cocoapp.repositories.ReviewRepository;
import com.server.cocoapp.repositories.ShopItemRepository;
import com.server.cocoapp.services.LocationService;
import com.server.cocoapp.services.ReviewService;
import com.server.cocoapp.services.ShopItemService;
import com.server.cocoapp.utils.RandomGenerator;
import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.entities.UserRole;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.dto.ReviewDto;
import com.server.cocoapp.entities.Location;
import com.server.cocoapp.entities.Review;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.exceptions.LocationNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;

import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class CoCoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoCoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository, ReviewRepository reviewRepository, LocationRepository locationRepository) {
		return args -> {
			RandomGenerator randomGenerator = new RandomGenerator();

			// List<Review> reviews = reviewRepository.findAll();

			// for (Review review : reviews) {
			// 	if (review.getTargetId().equals("66daa83ece738273ecd618aa")) {
			// 		Location location = locationRepository.findById(review.getTargetId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
			// 		location.getReviews().add(review);
			// 		locationRepository.save(location);
			// 	}
			// }
			

			// List<User> users = userRepository.findAll();
			// for (User user : users) {
			// 	user.setReviews(new ArrayList<>());
			// 	userRepository.save(user);
			// }


			
			// for (Location location : locations) {
			// 	if (location.getReviews() == null) location.setReviews(new ArrayList<>());
				
			// 	int numReviews = randomGenerator.genInt(50, 200);
			// 	int oneStars = randomGenerator.genInt(5, 15) * numReviews / 100;
			// 	int twoStars = randomGenerator.genInt(0, 5) * numReviews / 100;
			// 	int threeStars = randomGenerator.genInt(0, 5) * numReviews / 100;
			// 	int fourStars = randomGenerator.genInt(0, 10) * numReviews / 100;

			// 	for (int i = 1; i <= numReviews; ++i) {
			// 		ReviewDto review = new ReviewDto();
			// 		review.setDetail("The thing I like best about COCO is the amount of time it has saved while trying to manage my three pets.");
			// 		review.setType("location");
			// 		review.setTargetId(location.getId());
					
			// 		if (i <= oneStars) review.setRating(1);
			// 		else if (i <= oneStars + twoStars) review.setRating(2);
			// 		else if (i <= oneStars + twoStars + threeStars) review.setRating(3);
			// 		else if (i <= oneStars + twoStars + threeStars  + fourStars) review.setRating(4);
			// 		else review.setRating(5);

			// 		String username = String.valueOf(randomGenerator.genInt(1001, 1200));
			// 		reviewService.addReview(username, review);
			// 	}
			// }
		};
	}

}
