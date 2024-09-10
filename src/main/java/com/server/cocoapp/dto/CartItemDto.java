package com.server.cocoapp.dto;

import com.server.cocoapp.classes.CartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDto {
    private String id;
    private ShopItemDto item;
    private Integer quantity;

    public void update(CartItem cartItem) {
        if (cartItem.getItem() != null) {
            item = new ShopItemDto();
            item.update(cartItem.getItem());
        }

        quantity = cartItem.getQuantity();

        if (cartItem.getId() != null) id = cartItem.getId();
    }
}
