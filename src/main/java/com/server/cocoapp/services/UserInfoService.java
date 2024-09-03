package com.server.cocoapp.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.dto.UserDto;
import com.server.cocoapp.exceptions.UserNotFoundException;


@Service
public class UserInfoService {
    private final UserRepository userRepository;
    private final FileService fileService;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.static}")
    private String path;

    public UserInfoService(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }
    
    public UserDto getInfo(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        UserDto response = new UserDto();
        response.update(user);

        return response;
    }

    public UserDto updateUserInfo(UserDto userDto, MultipartFile file) throws IOException {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.update(userDto);

        if (file != null) {
            if (user.getImageName() != null) {
                fileService.deleteFile(path, user.getImageName());
            }

            String fileName = fileService.uploadFile(path, file);
            user.setImageName(fileName);
        }

        userRepository.save(user);

        UserDto response = new UserDto();
        response.update(user);

        return response;
    }
}
