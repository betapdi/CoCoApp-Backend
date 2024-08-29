package com.server.cocoapp.dto;

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
    private int votes;

    // Constructors, getters, setters (or use Lombok annotations)

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", votes=" + votes +
                '}';
    }
}
