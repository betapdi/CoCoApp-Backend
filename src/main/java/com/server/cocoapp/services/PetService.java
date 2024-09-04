package com.server.cocoapp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.dto.PetDto;
import com.server.cocoapp.entities.Pet;
import com.server.cocoapp.exceptions.PetNotFoundException;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.repositories.PetRepository;

@Service
public class PetService {
    private final FileService fileService;
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    
    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.static}")
    private String path;

    PetService(FileService fileService, PetRepository petRepository, UserRepository userRepository) {
        this.fileService = fileService;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public List<PetDto> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        List<PetDto> petDtos = new ArrayList<>();
        
        for (Pet currPet : pets) {
            PetDto petDto = new PetDto();
            petDto.update(currPet);

            petDtos.add(petDto);
        }

        return petDtos;
    } 

    public List<PetDto> getAllPetsUser(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        List<Pet> pets = user.getPets();
        List<PetDto> petDtos = new ArrayList<>();
        
        for (Pet currPet : pets) {
            PetDto petDto = new PetDto();
            petDto.update(currPet);

            petDtos.add(petDto);
        }

        return petDtos;
    } 

    public PetDto getPet(String petId) {
        Pet currPet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet not found!"));
        
        PetDto petDto = new PetDto();
        petDto.update(currPet);

        return petDto;
    }

    public PetDto addPet(PetDto petDto, MultipartFile file, String username) throws IOException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Pet newPet = new Pet();
        newPet.update(petDto);

        newPet.setOwnerId(user.getUserId());
        if (file != null) {
            String fileName = fileService.uploadFile(path, file);
            newPet.setImageName(fileName);
        }
        petRepository.save(newPet);
        
        user.getPets().add(newPet);
        userRepository.save(user);

        PetDto response = new PetDto();
        response.update(newPet);
        
        return response;
    }

    public String deletePet(String petId, String username) throws IOException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Pet currPet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet not found!"));

        user.getPets().removeIf(pet -> pet.getId().equals(currPet.getId()));
        
        if (currPet.getImageName() != null) {
            fileService.deleteFile(path, currPet.getImageName());
        }

        petRepository.delete(currPet);
        userRepository.save(user);
        return "Pet deleted with id: " + currPet.getId();
    }

    public PetDto updatePet(PetDto petDto, MultipartFile file) throws IOException {
        Pet currPet = petRepository.findById(petDto.getId()).orElseThrow(() -> new PetNotFoundException("Pet not found!"));

        currPet.update(petDto);

        if (file != null) {
            if (currPet.getImageName() != null) fileService.deleteFile(path, currPet.getImageName());
            String newFileName = fileService.uploadFile(path, file);
            currPet.setImageName(newFileName);
        }

        petRepository.save(currPet);

        PetDto newPetDto = new PetDto();
        newPetDto.update(currPet);
        
        return newPetDto;
    }
}
