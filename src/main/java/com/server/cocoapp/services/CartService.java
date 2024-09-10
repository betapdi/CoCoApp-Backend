package com.server.cocoapp.services;

import org.springframework.stereotype.Service;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.classes.CartItem;
import com.server.cocoapp.dto.CartDto;
import com.server.cocoapp.dto.CartItemDto;
import com.server.cocoapp.entities.Cart;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.exceptions.CartItemNotFoundException;
import com.server.cocoapp.exceptions.ShopItemNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.CartItemRepository;
import com.server.cocoapp.repositories.CartRepository;
import com.server.cocoapp.repositories.ShopItemRepository;

@Service
public class CartService {
    private final UserRepository userRepository;
    private final ShopItemRepository shopItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(UserRepository userRepository, ShopItemRepository shopItemRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.shopItemRepository = shopItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public CartDto getCart(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        CartDto cartDto = new CartDto();
        cartDto.update(user.getCart());
        
        return cartDto;
    }

    public CartDto addCartItem(String username, CartItemDto dto) {//cartItem.item just need an item id here
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        ShopItem item = shopItemRepository.findById(dto.getItem().getId()).orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(dto.getQuantity());

        cartItemRepository.save(cartItem);

        user.getCart().getItems().add(cartItem);
        cartRepository.save(user.getCart());

        CartDto cartDto = new CartDto();
        cartDto.update(user.getCart());

        return cartDto;
    }

    public CartDto updateCartItem(String username, CartItemDto dto) { //cartItem.item just need an item id here
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        CartItem cartItem = cartItemRepository.findById(dto.getId()).orElseThrow(() -> new CartItemNotFoundException("CartItem not found!"));
        Cart cart = user.getCart();
        
        cart.getItems().removeIf(currCartItem -> currCartItem.getId().equals(dto.getId()));

        if (dto.getQuantity() > 0) {
            cartItem.setQuantity(dto.getQuantity());
            cartItemRepository.save(cartItem);

            cart.getItems().add(cartItem);
        }

        else cartRepository.deleteById(dto.getId());

        cartRepository.save(cart);

        CartDto cartDto = new CartDto();
        cartDto.update(user.getCart());

        return cartDto;
    }
}
