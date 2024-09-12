package com.server.cocoapp.dto;

import java.util.List;
import java.util.ArrayList;

import com.server.cocoapp.classes.CartItem;
import com.server.cocoapp.entities.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private String id;
    private List<CartItemDto> items;

    private String userId;

    public void update(Cart cart) {
        if (cart.getId() != null) id = cart.getId();
        
        if (cart.getItems() != null) {
            items = new ArrayList<>();
            
            for (CartItem cartItem : cart.getItems()) {
                CartItemDto dto = new CartItemDto();
                dto.update(cartItem);

                items.add(dto);
            }
        }
        if (cart.getUserId() != null) userId = cart.getUserId();
    }
}
