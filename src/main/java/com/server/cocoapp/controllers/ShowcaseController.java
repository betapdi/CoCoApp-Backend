package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.cocoapp.dto.ShowcaseDto;
import com.server.cocoapp.services.ShowcaseService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
@RequestMapping("/api/v1/showcase")
public class ShowcaseController {
    private final ShowcaseService showcaseService;

    public ShowcaseController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShowcaseDto>> getAll() {
        List<ShowcaseDto> response = showcaseService.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/get/{showcaseId}")
    public ResponseEntity<ShowcaseDto> get(@PathVariable("showcaseId") String showcaseId) {
        ShowcaseDto response = showcaseService.get(showcaseId);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ShowcaseDto> addShowcase(@RequestPart("showcase") ShowcaseDto showcaseDto) {
        ShowcaseDto response = showcaseService.addShowcase(showcaseDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{showcaseId}")
    public ResponseEntity<String> delete(@PathVariable("showcaseId") String showcaseId) {
        String response = showcaseService.deleteShowcase(showcaseId);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ShowcaseDto> update(@RequestPart("showcase") ShowcaseDto showcaseDto) {
        ShowcaseDto response = showcaseService.updateShowcase(showcaseDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
