package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.dto.ShopItemDto;
import com.server.cocoapp.services.ShopItemService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("api/v1/shopItem")
@AllArgsConstructor
public class ShopItemController {
    private final ShopItemService shopItemService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ShopItemDto>> fetchAllShopItems() {
        return ResponseEntity.ok(shopItemService.getAllShopItems());
    }

    @GetMapping("/get/{shopItemId}")
    public ResponseEntity<ShopItemDto> getShopItem(@PathVariable("shopItemId") String shopItemId) {
        ShopItemDto response = shopItemService.getShopItem(shopItemId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @PostMapping("/add")
    public ResponseEntity<ShopItemDto> addShopItem(@RequestPart("image") MultipartFile file, 
                                                    @RequestPart("shopItem") ShopItemDto shopItemDto) throws IOException {
        ShopItemDto response = shopItemService.addShopItem(shopItemDto, file);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{shopItemId}")
    public ResponseEntity<String> deleteShopItem(@PathVariable("shopItemId") String shopItemId) throws IOException {
        String response = shopItemService.deleteShopItem(shopItemId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ShopItemDto> updateShopItem(@RequestPart("image") MultipartFile file, 
                                                        @RequestPart("shopItem") ShopItemDto shopItemDto) throws IOException {
        return ResponseEntity.ok(shopItemService.updateShopItem(shopItemDto, file));
    }
    
}
