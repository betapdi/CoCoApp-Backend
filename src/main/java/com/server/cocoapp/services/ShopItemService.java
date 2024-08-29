package com.server.cocoapp.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.server.cocoapp.dto.ShopItemDto;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.exceptions.ShopItemNotFoundException;
import com.server.cocoapp.repositories.ShopItemRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ShopItemService {
    private final ShopItemRepository shopItemRepository;

    public List<ShopItem> getAllShopItems() {
        return shopItemRepository.findAll();
    }

    public ShopItemDto addShopItem(ShopItemDto shopItemDto) {
        ShopItem newItem = new ShopItem();

        BeanUtils.copyProperties(shopItemDto, newItem);
        shopItemRepository.save(newItem);

        shopItemDto.setId(newItem.getId());
        return shopItemDto;
    }

    public String deleteShopItem(ShopItemDto shopItemDto) throws IOException {
        ShopItem item = shopItemRepository.findById(shopItemDto.getId())
                    .orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        shopItemRepository.delete(item);
        return "Movie deleted with id = " + shopItemDto.getId();
    }

    public ShopItemDto updateShopItem(ShopItemDto shopItemDto) {
        try {
            ShopItem item = shopItemRepository.findById(shopItemDto.getId()).get();
    
            BeanUtils.copyProperties(shopItemDto, item);
            shopItemRepository.save(item);
    
            return shopItemDto;
        }

        catch (Exception e) {
            System.out.println(e);
            return shopItemDto;
        }
    }
}
