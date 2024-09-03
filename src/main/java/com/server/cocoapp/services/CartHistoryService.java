package com.server.cocoapp.services;

import org.springframework.stereotype.Service;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.classes.CartItem;
import com.server.cocoapp.dto.CartHistoryDto;
import com.server.cocoapp.dto.CartItemDto;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.exceptions.ShopItemNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.ShopItemRepository;

@Service
public class CartHistoryService {
    private final UserRepository userRepository;
    private final ShopItemRepository shopItemRepository;

    public CartHistoryService(UserRepository userRepository, ShopItemRepository shopItemRepository) {
        this.userRepository = userRepository;
        this.shopItemRepository = shopItemRepository;
    }

    public CartHistoryDto getCart(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        CartHistoryDto cartDto = new CartHistoryDto();
        cartDto.update(user.getCart());
        
        return cartDto;
    }

    public CartHistoryDto addCartItem(String username, CartItemDto dto) {//cartItem.item just need an item id here
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        ShopItem item = shopItemRepository.findById(dto.getItem().getId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(dto.getQuantity());

        user.getCart().getItems().add(cartItem);
        userRepository.save(user);

        CartHistoryDto cartDto = new CartHistoryDto();
        cartDto.update(user.getCart());

        return cartDto;
    }

    public CartHistoryDto updateCartItem(String username, CartItemDto dto) { //cartItem.item just need an item id here
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        ShopItem item = shopItemRepository.findById(dto.getItem().getId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        user.getCart().getItems().removeIf(currCartItem -> currCartItem.getItem().getId().equals(dto.getItem().getId()));
        if (dto.getQuantity() > 0) {
            CartItem newCartItem = new CartItem();
            newCartItem.setItem(item);
            newCartItem.setQuantity(dto.getQuantity());

            user.getCart().getItems().add(newCartItem);
        }

        userRepository.save(user);

        CartHistoryDto cartDto = new CartHistoryDto();
        cartDto.update(user.getCart());

        return cartDto;
    }
}
