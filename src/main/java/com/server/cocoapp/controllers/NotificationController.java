package com.server.cocoapp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.cocoapp.dto.NotificationDto;
import com.server.cocoapp.services.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<NotificationDto>> getAll() {
        List<NotificationDto> response = notificationService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/getUser")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@AuthenticationPrincipal UserDetails userDetails) {
        List<NotificationDto> response = notificationService.getUserNotifications(userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{notificationId}")
    public ResponseEntity<NotificationDto> getNotification(@PathVariable("notificationId") String notificationId) {
        NotificationDto response = notificationService.getNotification(notificationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NotificationDto> addNotification(@AuthenticationPrincipal UserDetails userDetails, @RequestPart("notification") NotificationDto notificationDto) {
        NotificationDto response = notificationService.addNotification(userDetails.getUsername(), notificationDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{notificationId}")
    public ResponseEntity<String> deleteNotification(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("notificationId") String notificationId) {
        String response = notificationService.deleteNotification(userDetails.getUsername(), notificationId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/update")
    public ResponseEntity<NotificationDto> deleteNotification(@AuthenticationPrincipal UserDetails userDetails, @RequestPart("notification") NotificationDto notificationDto) {
        NotificationDto response = notificationService.updateNotification(userDetails.getUsername(), notificationDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
