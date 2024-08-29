package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.cocoapp.dto.ShopItemDto;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.services.ShopItemService;

import lombok.AllArgsConstructor;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/shopItem")
@AllArgsConstructor
public class ShopItemController {
    private final ShopItemService shopItemService;

    @GetMapping
    public ResponseEntity<List<ShopItem>> fetchAllShopItems() {
        return ResponseEntity.ok(shopItemService.getAllShopItems());
    }

    @PostMapping("/add")
    public ResponseEntity<ShopItemDto> addShopItem(@RequestBody ShopItemDto shopItemDto) {
        return ResponseEntity.ok(shopItemService.addShopItem(shopItemDto));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteShopItem(@RequestBody ShopItemDto shopItemDto) throws IOException {
        String response = shopItemService.deleteShopItem(shopItemDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ShopItemDto> updateShopItem(@RequestBody ShopItemDto shopItemDto) {
        return ResponseEntity.ok(shopItemService.updateShopItem(shopItemDto));
    }
    
}
