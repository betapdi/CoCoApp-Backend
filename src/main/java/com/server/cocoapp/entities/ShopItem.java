package com.server.cocoapp.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.classes.Size;
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

    private String brand;

    private int discount;

    @Builder.Default
    private int stock = 99999;

    private String description;

    @Builder.Default
    List<Review> reviews = new ArrayList<>();
    
    private String imageName;

    private Size size;

    @Builder.Default
    private int quantitySold = 50;

    public void update(ShopItemDto shopItemDto) {
        if (shopItemDto.getName() != null) this.name = shopItemDto.getName();
        if (shopItemDto.getCategory() != null) this.category = shopItemDto.getCategory();
        if (shopItemDto.getPrice() != 0) this.price = shopItemDto.getPrice();
        if (shopItemDto.getStock() != 0) this.stock = shopItemDto.getStock();
        if (shopItemDto.getDescription() != null) this.description = shopItemDto.getDescription();
        if (shopItemDto.getSize() != null) this.size = shopItemDto.getSize();
        if (shopItemDto.getQuantitySold() != 0) this.quantitySold = shopItemDto.getQuantitySold();
        if (shopItemDto.getBrand() != null) this.brand = shopItemDto.getBrand();
        if (shopItemDto.getDiscount() != 0) this.discount = shopItemDto.getDiscount();
    }
}
