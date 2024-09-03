package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.dto.NotificationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    private String id;

    private String title;

    private String body;

    public void update(NotificationDto dto) {
        if (dto.getId() != null) id = dto.getId();
        if (dto.getTitle() != null) title = dto.getTitle();
        if (dto.getBody() != null) body = dto.getBody();
    }
}
