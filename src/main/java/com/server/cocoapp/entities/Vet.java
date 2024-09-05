package com.server.cocoapp.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.classes.Appointment;
import com.server.cocoapp.dto.VetDto;

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
public class Vet {
    @Id
    private String vetId;

    private String vetName;

    private String degree;

    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();

    private String description;

    private String experience;

    @DBRef
    @Builder.Default
    private Location location = new Location();

    public void update(VetDto dto) {
        if (dto.getVetName() != null) vetName = dto.getVetName();
        if (dto.getDegree() != null) degree = dto.getDegree();
        if (dto.getAppointments() != null) appointments = dto.getAppointments();
        if (dto.getDescription() != null) description = dto.getDescription();
        if (dto.getExperience() != null) experience = dto.getExperience();
        
        if (dto.getLocation() != null) {
            location.update(dto.getLocation());
        }
    }
}
