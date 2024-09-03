package com.server.cocoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.dto.VetDto;
import com.server.cocoapp.entities.Vet;
import com.server.cocoapp.exceptions.VetNotFoundException;
import com.server.cocoapp.repositories.VetRepository;

import java.io.IOException;

public class VetService {
    private VetRepository vetRepository;
    private FileService fileService;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.static}")
    private String path;
    
    public VetService(VetRepository vetRepository, FileService fileService) {
        this.vetRepository = vetRepository;
        this.fileService = fileService;
    }

    public List<VetDto> getAll() {
        List<Vet> vets = vetRepository.findAll();
        List<VetDto> response = new ArrayList<>();

        for (Vet vet : vets) {
            VetDto dto = new VetDto();
            dto.update(vet);

            response.add(dto);
        }

        return response;
    }

    public VetDto getVet(String vetId) {
        Vet vet = vetRepository.findById(vetId).orElseThrow(() -> new VetNotFoundException("Vet not found!"));

        VetDto response = new VetDto();
        response.update(vet);

        return response;
    }

    public VetDto addVet(MultipartFile file, VetDto vetDto) throws IOException {
        Vet vet = new Vet();
        vet.update(vetDto);

        if (file != null) {
            String fileName = fileService.uploadFile(path, file);
            vet.getLocation().setImageName(fileName);
        }

        vetRepository.save(vet);

        VetDto response = new VetDto();
        response.update(vet);

        return response;
    }

    public String deleteVet(String vetId) throws IOException {
        Vet vet = vetRepository.findById(vetId).orElseThrow(() -> new VetNotFoundException("Vet not found!"));

        if (vet.getLocation().getImageName() != null) {
            fileService.deleteFile(path, vet.getLocation().getImageName());
        }

        vetRepository.delete(vet);

        return "Vet deleted with id: " + vet.getVetId();
    }

    public VetDto updateVet(MultipartFile file, VetDto vetDto) throws IOException {
        Vet vet = vetRepository.findById(vetDto.getVetId()).orElseThrow(() -> new VetNotFoundException("Vet not found!"));

        vet.update(vetDto);

        if (file != null) {
            if (vet.getLocation().getImageName() != null) {
                fileService.deleteFile(path, vet.getLocation().getImageName());
            }

            String fileName = fileService.uploadFile(path, file);
            vet.getLocation().setImageName(fileName);
        }

        vetRepository.save(vet);

        VetDto response = new VetDto();
        response.update(vet);

        return response;
    }
}
