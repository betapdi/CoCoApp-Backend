package com.server.cocoapp.services;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.classes.CartItem;
import com.server.cocoapp.dto.CartDto;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.CartRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public CartDto getCart(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(user.getCart(), cartDto);
        
        return cartDto;
    }

    public CartDto addCartItem(String username, CartItem cartItem) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        user.getCart().getItems().add(cartItem);
        userRepository.save(user);

        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(user.getCart(), cartDto);

        return cartDto;
    }

    public CartDto updateCartItem(String username, CartItem cartItem) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        user.getCart().getItems().removeIf(currCartItem -> currCartItem.getItem().getId().equals(cartItem.getItem().getId()));
        if (cartItem.getQuantity() > 0) user.getCart().getItems().add(cartItem);

        userRepository.save(user);

        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(user.getCart(), cartDto);

        return cartDto;
    }
}
