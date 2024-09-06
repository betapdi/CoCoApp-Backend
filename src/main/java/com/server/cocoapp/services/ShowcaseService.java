package com.server.cocoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.cocoapp.entities.Showcase;
import com.server.cocoapp.entities.Vet;
import com.server.cocoapp.exceptions.ShowcaseNotFoundException;
import com.server.cocoapp.exceptions.VetNotFoundException;
import com.server.cocoapp.dto.ShowcaseDto;

import com.server.cocoapp.repositories.ShowcaseRepository;
import com.server.cocoapp.repositories.VetRepository;

@Service
public class ShowcaseService {
    private final ShowcaseRepository showcaseRepository;
    private final VetRepository vetRepository;

    public ShowcaseService(ShowcaseRepository showcaseRepository, VetRepository vetRepository) {
        this.showcaseRepository = showcaseRepository;
        this.vetRepository = vetRepository;
    }

    public List<ShowcaseDto> getAll() {
        List<Showcase> showcases = showcaseRepository.findAll();
        List<ShowcaseDto> dtos = new ArrayList<>();
        
        for (Showcase showcase : showcases) {
            ShowcaseDto showcaseDto = new ShowcaseDto();
            showcaseDto.update(showcase);
            dtos.add(showcaseDto);
        }

        return dtos;
    }
    
    public ShowcaseDto get(String showcaseId) {
        Showcase showcase = showcaseRepository.findById(showcaseId).orElseThrow(() -> new ShowcaseNotFoundException("Showcase not found!"));
        ShowcaseDto showcaseDto = new ShowcaseDto();
        showcaseDto.update(showcase);

        return showcaseDto;
    }

    public ShowcaseDto addShowcase(ShowcaseDto dto) {
        Vet vet = vetRepository.findById(dto.getVet().getVetId()).orElseThrow(() -> new VetNotFoundException("Vet not found!"));
        
        Showcase showcase = new Showcase();
        showcase.update(dto);
        showcase.setVet(vet);
        showcaseRepository.save(showcase);

        ShowcaseDto response = new ShowcaseDto();
        response.update(showcase);
        
        return response;
    }

    public String deleteShowcase(String showcaseId) {
        Showcase showcase = showcaseRepository.findById(showcaseId).orElseThrow(() -> new ShowcaseNotFoundException("Showcase not found!"));
        showcaseRepository.delete(showcase);

        return "Showcase deleted with id: " + showcaseId;
    }

    public ShowcaseDto updateShowcase(ShowcaseDto dto) {
        Vet vet = vetRepository.findById(dto.getVet().getVetId()).orElseThrow(() -> new VetNotFoundException("Vet not found!"));
        Showcase showcase = showcaseRepository.findById(dto.getId()).orElseThrow(() -> new ShowcaseNotFoundException("Showcase not found!"));
        
        showcase.update(dto);
        if (vet != null) showcase.setVet(vet);
        showcaseRepository.save(showcase);

        ShowcaseDto response = new ShowcaseDto();
        response.update(showcase);
        
        return response;
    }
}
