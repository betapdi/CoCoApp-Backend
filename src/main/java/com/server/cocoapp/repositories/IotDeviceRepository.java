package com.server.cocoapp.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.cocoapp.entities.IotDevice;

public interface IotDeviceRepository extends MongoRepository<IotDevice, String> {
}