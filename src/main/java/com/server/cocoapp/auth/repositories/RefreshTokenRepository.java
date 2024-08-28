package com.springboot.test.movieApi.auth.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.test.movieApi.auth.entities.RefreshToken;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
