package com.server.cocoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.dto.NotificationDto;
import com.server.cocoapp.entities.Notification;
import com.server.cocoapp.exceptions.NotificationNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.NotificationRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<NotificationDto> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationDto> dtos = new ArrayList<>();

        for (Notification noti : notifications) {
            NotificationDto dto = new NotificationDto();
            dto.update(noti);

            dtos.add(dto);
        }

        return dtos;
    }

    public List<NotificationDto> getUserNotifications(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        List<NotificationDto> dtos = new ArrayList<>();

        for (Notification noti : user.getNotifications()) {
            NotificationDto dto = new NotificationDto();
            dto.update(noti);

            dtos.add(dto);
        }

        return dtos;
    }
    
    public NotificationDto getNotification(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                                    .orElseThrow(() -> new NotificationNotFoundException("Notification not found!"));
        
        NotificationDto response = new NotificationDto();
        response.update(notification);

        return response;
    }

    public NotificationDto addNotification(String username, NotificationDto notificationDto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        Notification noti = new Notification();
        noti.update(notificationDto);
        notificationRepository.save(noti);

        user.getNotifications().add(noti);
        userRepository.save(user);

        NotificationDto response = new NotificationDto();
        response.update(noti);

        return response;
    }

    public String deleteNotification(String username, String notificationId) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        
        user.getNotifications().removeIf(notifi -> notifi.getId().equals(notificationId));
        userRepository.save(user);

        notificationRepository.deleteById(notificationId);

        return "Notification deleted with id: " + notificationId;
    }

    public NotificationDto updateNotification(String username, NotificationDto notificationDto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Notification notification = notificationRepository.findById(notificationDto.getId())
                                    .orElseThrow(() -> new NotificationNotFoundException("Notification not found!"));

        user.getNotifications().removeIf(notifi -> notifi.getId().equals(notificationDto.getId()));     

        notification.update(notificationDto);
        notificationRepository.save(notification);

        user.getNotifications().add(notification);
        userRepository.save(user);

        NotificationDto response = new NotificationDto();
        response.update(notification);

        return response;
    }
}
