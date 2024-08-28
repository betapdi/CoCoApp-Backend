package com.server.cocoapp.entities;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @DBRef
    List<CartItem> items;

    public float getTotalPrices() {
        float answer = 0;
        for (CartItem cartItem : items) {
            answer += cartItem.getQuantity() * cartItem.getItem().getPrice();
        }

        return answer;
    }
}
