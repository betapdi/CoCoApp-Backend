package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.classes.Appointment;
import com.server.cocoapp.dto.PetDto;
import com.server.cocoapp.dto.ReviewDto;
import com.server.cocoapp.dto.UserDto;
import com.server.cocoapp.services.UserInfoService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping("/getInfo")
    public ResponseEntity<UserDto> fetchUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userInfoService.getInfo(userDetails.getUsername()));
    }

    @GetMapping("/getInfoId/{userId}")
    public ResponseEntity<UserDto> fetchUserInfoId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userInfoService.getInfoId(userId));
    }

    @GetMapping("/getPets")
    public ResponseEntity<List<PetDto>> fetchPets(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userInfoService.getPets(userDetails.getUsername()));
    }

    @GetMapping("/getReviews")
    public ResponseEntity<List<ReviewDto>> fetchReviews(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userInfoService.getReviews(userDetails.getUsername()));
    }

    @GetMapping("/getAppointments")
    public ResponseEntity<List<Appointment>> fetchAppointments(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userInfoService.getAppointments(userDetails.getUsername()));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUserInfo(@RequestPart("image") MultipartFile file, 
                                                        @RequestPart("user") UserDto userDto) throws IOException {
        return ResponseEntity.ok(userInfoService.updateUserInfo(userDto, file));
    }
    
    @GetMapping("/getAppointmentHistory")
    public ResponseEntity<List<Appointment>> getAppointmentHistory(@AuthenticationPrincipal UserDetails userDetails) {
        List<Appointment> response = userInfoService.getAppointmentHistory(userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addAppointmentHistory")
    public ResponseEntity<String> addAppointmentHistory(@AuthenticationPrincipal UserDetails userDetails, @RequestPart("appointment") Appointment appointment) {
        String response = userInfoService.addAppointmentHistory(userDetails.getUsername(), appointment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
}
