package com.server.cocoapp.dto;

import com.server.cocoapp.entities.Showcase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowcaseDto {
    private String id;

    private VetDto vet;

    private String category;

    private String type;

    private String description;

    public void update(Showcase obj) {
        if (obj.getId() != null) id = obj.getId();
        if (obj.getCategory() != null) category = obj.getCategory();
        if (obj.getType() != null) type = obj.getType();
        if (obj.getDescription() != null) description = obj.getDescription();

        if (obj.getVet() != null) {
            vet = new VetDto();
            vet.update(obj.getVet());
        }
    }
}