package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.ShopItem;

public interface ShopItemRepository extends MongoRepository<ShopItem, String> {
}