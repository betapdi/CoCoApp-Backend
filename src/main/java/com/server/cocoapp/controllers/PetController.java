package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.dto.PetDto;
import com.server.cocoapp.services.PetService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/pet")
@AllArgsConstructor
public class PetController {
    private final PetService PetService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PetDto>> fetchAllPets() {
        return ResponseEntity.ok(PetService.getAllPets());
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<PetDto>> fetchAllPetsUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(PetService.getAllPetsUser(userDetails.getUsername()));
    }

    @GetMapping("/get")
    public ResponseEntity<PetDto> getPet(@RequestPart("petId") String petId) {
        PetDto response = PetService.getPet(petId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @PostMapping("/add")
    public ResponseEntity<PetDto> addPet(@RequestPart("image") MultipartFile file, 
                                        @RequestPart("pet") PetDto petDto,
                                        @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        PetDto response = PetService.addPet(petDto, file, userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePet(@RequestPart("petId") String petId,
                                            @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        String response = PetService.deletePet(petId, userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<PetDto> updatePet(@RequestPart("image") MultipartFile file, 
                                            @RequestPart("pet") PetDto petDto) throws IOException {
        return ResponseEntity.ok(PetService.updatePet(petDto, file));
    }
    
}
