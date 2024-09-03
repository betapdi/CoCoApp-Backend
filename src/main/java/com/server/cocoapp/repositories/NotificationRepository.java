package com.server.cocoapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}