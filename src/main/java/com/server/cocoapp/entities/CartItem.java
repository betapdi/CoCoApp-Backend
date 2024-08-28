package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
public class CartItem {
    @Id
    @Indexed
    private String id;

    @DBRef
    private ShopItem item;

    @Builder.Default
    private Integer quantity = 0;
}
