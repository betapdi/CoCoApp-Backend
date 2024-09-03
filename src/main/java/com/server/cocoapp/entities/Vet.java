package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
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

    private long dateAppointment;

    private String description;

    @DBRef
    @Builder.Default
    private Location location = new Location();

    public void update(VetDto dto) {
        if (dto.getVetName() != null) vetName = dto.getVetName();
        if (dto.getDegree() != null) degree = dto.getDegree();
        if (dto.getDateAppointment() != 0) dateAppointment = dto.getDateAppointment();
        if (dto.getDescription() != null) description = dto.getDescription();
        
        if (dto.getLocation() != null) {
            location.update(dto.getLocation());
        }
    }
}
