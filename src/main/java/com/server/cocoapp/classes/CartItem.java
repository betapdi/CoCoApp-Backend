package com.server.cocoapp.classes;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.server.cocoapp.entities.ShopItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    @DBRef
    private ShopItem item;

    @Builder.Default
    private Integer quantity = 0;
}
