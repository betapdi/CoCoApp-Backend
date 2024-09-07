package com.server.cocoapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.cocoapp.dto.ShowcaseDto;

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
public class Showcase {
    @Id
    @Indexed
    private String id;

    @DBRef
    private Vet vet;

    private String category;

    private String type;

    private String description;

    public void update(ShowcaseDto dto) {
        if (dto.getCategory() != null) category = dto.getCategory();
        if (dto.getType() != null) type = dto.getType();
        if (dto.getDescription() != null) description = dto.getDescription();
    }
}
