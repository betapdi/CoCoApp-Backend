package com.server.cocoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.cocoapp.dto.VetDto;
import com.server.cocoapp.services.VetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/api/v1/vet")
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<VetDto>> getAll() {
        List<VetDto> response = vetService.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{vetId}")
    public ResponseEntity<VetDto> getVet(@PathVariable("vetId") String vetId) {
        VetDto response = vetService.getVet(vetId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<VetDto> addVet(@RequestPart("image") MultipartFile file,
                                                    @RequestPart("vet") VetDto vetDto) throws IOException {
        VetDto response = vetService.addVet(file, vetDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/delete/{vetId}")
    public ResponseEntity<String> deleteVet(@PathVariable("vetId") String vetId) throws IOException {
        String response = vetService.deleteVet(vetId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<VetDto> updateVet(@RequestPart("image") MultipartFile file, 
                                                        @RequestPart("vet") VetDto vetDto) throws IOException {
        VetDto response = vetService.updateVet(file, vetDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    


    
}
