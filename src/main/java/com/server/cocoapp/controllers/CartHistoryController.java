package com.server.cocoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.cocoapp.dto.CartHistoryDto;
import com.server.cocoapp.dto.CartItemDto;
import com.server.cocoapp.services.CartHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/api/v1/cartHistory")
public class CartHistoryController {
    private final CartHistoryService cartHistoryService;

    public CartHistoryController(CartHistoryService cartHistoryService) {
        this.cartHistoryService = cartHistoryService;
    }

    @GetMapping("/get")
    public ResponseEntity<CartHistoryDto> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        CartHistoryDto response = cartHistoryService.getCart(userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addItem")
    public ResponseEntity<CartHistoryDto> addCartItem(@AuthenticationPrincipal UserDetails userDetails, @RequestPart CartItemDto cartItemDto) {
        CartHistoryDto response = cartHistoryService.addCartItem(userDetails.getUsername(), cartItemDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateItem")
    public ResponseEntity<CartHistoryDto> updateCartItem(@AuthenticationPrincipal UserDetails userDetails, @RequestPart CartItemDto cartItemDto) {
        CartHistoryDto response = cartHistoryService.updateCartItem(userDetails.getUsername(), cartItemDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }    
}
