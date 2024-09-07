package com.server.cocoapp.classes;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    private String id;
    private long time;
    private String vetId;
    private String userId;
    private String category;
    private String type;
}
