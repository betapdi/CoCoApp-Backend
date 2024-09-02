package com.server.cocoapp.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.classes.CartItem;

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
    @Id
    @Indexed
    String id;

    @DBRef
    List<CartItem> items;

    String userId;

    public Cart(String userId) {
        this.userId = userId;
        items = new ArrayList<>();
    }
}
