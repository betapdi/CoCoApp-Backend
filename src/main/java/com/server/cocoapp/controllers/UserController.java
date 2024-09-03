package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.dto.UserDto;
import com.server.cocoapp.services.UserInfoService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/shopItem")
@AllArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping("/getInfo")
    public ResponseEntity<UserDto> fetchUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userInfoService.getInfo(userDetails.getUsername()));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUserInfo(@RequestPart("image") MultipartFile file, 
                                                        @RequestPart("user") UserDto userDto) throws IOException {
        return ResponseEntity.ok(userInfoService.updateUserInfo(userDto, file));
    }
    
}
