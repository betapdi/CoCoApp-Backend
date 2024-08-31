package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    
    private byte[] image;
}
