package com.server.cocoapp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.dto.ShopItemDto;
import com.server.cocoapp.entities.ShopItem;
import com.server.cocoapp.exceptions.ShopItemNotFoundException;
import com.server.cocoapp.repositories.ShopItemRepository;

@Service
public class ShopItemService {
    private final ShopItemRepository shopItemRepository;
    private final FileService fileService;

    @Value("${project.static}")
    private String path;
    
    @Value("${base.url}")
    private String baseUrl;
    
    public ShopItemService(ShopItemRepository shopItemRepository, FileService fileService) {
        this.shopItemRepository = shopItemRepository;
        this.fileService = fileService;
    }

    public List<ShopItemDto> getAllShopItems() {
        List<ShopItem> shopItems = shopItemRepository.findAll();
        List<ShopItemDto> shopItemDtos = new ArrayList<>();

        for (ShopItem item : shopItems) {
            ShopItemDto shopItemDto = new ShopItemDto();
            shopItemDto.update(item);

            shopItemDtos.add(shopItemDto);
        }

        return shopItemDtos;
    }

    public ShopItemDto getShopItem(String shopItemId) {
        ShopItem item = shopItemRepository.findById(shopItemId)
                    .orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        ShopItemDto shopItemDto = new ShopItemDto();
        shopItemDto.update(item);

        return shopItemDto;
    }

    public ShopItemDto addShopItem(ShopItemDto shopItemDto, MultipartFile file) throws IOException {
        ShopItem newItem = new ShopItem();

        newItem.update(shopItemDto);

        if (!file.isEmpty()) {
            String fileName = fileService.uploadFile(path, file);
            newItem.setImageName(fileName);
        }
        
        shopItemRepository.save(newItem);

        ShopItemDto newShopItemDto = new ShopItemDto();
        newShopItemDto.update(newItem);
        
        return newShopItemDto;
    }

    public String deleteShopItem(String shopItemId) throws IOException {
        ShopItem item = shopItemRepository.findById(shopItemId)
                    .orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));

        String imageName = item.getImageName();
        if (imageName != null) {
            fileService.deleteFile(path, imageName);
        }

        shopItemRepository.delete(item);
        return "ShopItem deleted with id = " + shopItemId;
    }

    public ShopItemDto updateShopItem(ShopItemDto shopItemDto, MultipartFile file) throws IOException {
        ShopItem item = shopItemRepository.findById(shopItemDto.getId())
                    .orElseThrow(() -> new ShopItemNotFoundException("ShopItem not found!"));
        
        item.update(shopItemDto);

        if (!file.isEmpty()) {
            if (item.getImageName() != null) fileService.deleteFile(path, item.getImageName());
            String newFileName = fileService.uploadFile(path, file);
            item.setImageName(newFileName);
        }

        shopItemRepository.save(item);

        ShopItemDto newShopItemDto = new ShopItemDto();
        newShopItemDto.update(item);
        
        return newShopItemDto;
    }
}
