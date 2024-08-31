package com.server.cocoapp.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    public String deleteShopItem(ShopItemDto shopItemDto) {
        ShopItem item = shopItemRepository.findById(shopItemDto.getId())
                    .orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        shopItemRepository.delete(item);
        return "ShopItem deleted with id = " + shopItemDto.getId();
    }

    public ShopItemDto updateShopItem(ShopItemDto shopItemDto) {
        ShopItem item = shopItemRepository.findById(shopItemDto.getId())
                    .orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));
    
        BeanUtils.copyProperties(shopItemDto, item);
        shopItemRepository.save(item);

        return shopItemDto;
    }
}
