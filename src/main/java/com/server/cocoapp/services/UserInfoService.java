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
import com.server.cocoapp.classes.Appointment;
import com.server.cocoapp.dto.PetDto;
import com.server.cocoapp.dto.ReviewDto;
import com.server.cocoapp.dto.UserDto;
import com.server.cocoapp.entities.Pet;
import com.server.cocoapp.entities.Review;
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

    public List<PetDto> getPets(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        List<PetDto> dtos = new ArrayList<>();

        for (Pet pet : user.getPets()) {
            PetDto dto = new PetDto();
            dto.update(pet);

            dtos.add(dto);
        }

        return dtos;
    }

    public List<ReviewDto> getReviews(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        List<ReviewDto> dtos = new ArrayList<>();

        for (Review review : user.getReviews()) {
            ReviewDto dto = new ReviewDto();
            dto.update(review);

            dtos.add(dto);
        }

        return dtos;
    }

    public List<Appointment> getAppointments(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        return user.getAppointments();
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
