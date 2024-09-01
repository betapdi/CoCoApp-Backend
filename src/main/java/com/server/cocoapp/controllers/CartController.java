package com.server.cocoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.cocoapp.classes.CartItem;
import com.server.cocoapp.dto.CartDto;
import com.server.cocoapp.services.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get")
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        CartDto response = cartService.getCart(userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addItem")
    public ResponseEntity<CartDto> addCartItem(@AuthenticationPrincipal UserDetails userDetails, @RequestPart CartItem cartItem) {
        CartDto response = cartService.addCartItem(userDetails.getUsername(), cartItem);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateItem")
    public ResponseEntity<CartDto> updateCartItem(@AuthenticationPrincipal UserDetails userDetails, @RequestPart CartItem cartItem) {
        CartDto response = cartService.updateCartItem(userDetails.getUsername(), cartItem);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }    
}
