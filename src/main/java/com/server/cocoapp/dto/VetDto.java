package com.server.cocoapp.dto;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.server.cocoapp.entities.Vet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VetDto {
    private String vetId;

    private String vetName;

    private String degree;

    private long dateAppointment;

    private String description;

    private LocationDto location;

    public void update(Vet vet) {
        if (vet.getVetId() != null) vetId = vet.getVetId();
        if (vet.getVetName() != null) vetName = vet.getVetName();
        if (vet.getDegree() != null) degree = vet.getDegree();
        if (vet.getDateAppointment() != 0) dateAppointment = vet.getDateAppointment();
        if (vet.getDescription() != null) description = vet.getDescription();
        
        if (vet.getLocation() != null) {
            location = new LocationDto();
            location.update(vet.getLocation());
        }
    }
}
