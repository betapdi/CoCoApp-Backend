package com.server.cocoapp.dto;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;

import com.server.cocoapp.classes.Size;
import com.server.cocoapp.entities.Review;
import com.server.cocoapp.entities.ShopItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopItemDto {
    private String id;
    private String name;
    private String category;
    private float price;
    private int stock;
    private String description;
    private float rating;
    private Integer votes;

    private Size size;
    
    private int quantitySold;
    private int discount;

    private String brand;

    @Value("${base.url}")
    private String imageUrl;

    private List<ReviewDto> reviews;

    // Constructors, getters, setters (or use Lombok annotations)

    public void update(ShopItem item) {
        if (item.getId() != null) this.id = item.getId();
        if (item.getName() != null) this.name = item.getName();
        if (item.getCategory() != null) this.category = item.getCategory();
        if (item.getPrice() != 0) this.price = item.getPrice();
        if (item.getStock() != 0) this.stock = item.getStock();
        if (item.getDescription() != null) this.description = item.getDescription();
        if (item.getSize() != null) this.size = item.getSize();
        if (item.getQuantitySold() != 0) this.quantitySold = item.getQuantitySold();
        if (item.getBrand() != null) this.brand = item.getBrand();
        if (item.getDiscount() != 0) this.discount = item.getDiscount();

        if (item.getReviews() != null) {
            reviews = new ArrayList<>();
            float sumRating = 0;
            votes = item.getReviews().size();

            for (Review review : item.getReviews()) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.update(review);

                sumRating += review.getRating();
                reviews.add(reviewDto);
            }

            if (votes > 0) rating = sumRating / votes;
            else rating = 0;
        }

        if (item.getImageName() != null) imageUrl = "/file/" + item.getImageName(); 
        
    }
}
