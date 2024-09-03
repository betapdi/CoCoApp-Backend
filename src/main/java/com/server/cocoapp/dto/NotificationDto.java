package com.server.cocoapp.dto;

import com.server.cocoapp.entities.Notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDto {
    private String id;

    private String title;

    private String body;

    public void update(Notification noti) {
        if (noti.getId() != null) id = noti.getId();
        if (noti.getTitle() != null) title = noti.getTitle();
        if (noti.getBody() != null) body = noti.getBody();
    }
}
