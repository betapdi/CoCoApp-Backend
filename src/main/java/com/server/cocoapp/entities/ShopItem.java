package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.dto.ShopItemDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopItem {
    @Id
    @Indexed
    private String id;

    @NonNull
    private String name;

    private String category;

    private float price;

    @Builder.Default
    private int stock = 100;

    private String description;

    private float rating;
    
    private int votes;
    
    private String imageName;

    public void update(ShopItemDto shopItemDto) {
        if (shopItemDto.getId() != null) this.id = shopItemDto.getId();
        if (shopItemDto.getName() != null) this.name = shopItemDto.getName();
        if (shopItemDto.getCategory() != null) this.category = shopItemDto.getCategory();
        if (shopItemDto.getPrice() != 0.0f) this.price = shopItemDto.getPrice();
        if (shopItemDto.getStock() != 0) this.stock = shopItemDto.getStock();
        if (shopItemDto.getDescription() != null) this.description = shopItemDto.getDescription();
        if (shopItemDto.getRating() != 0.0f) this.rating = shopItemDto.getRating();
        if (shopItemDto.getVotes() != 0) this.votes = shopItemDto.getVotes();
    }
}
