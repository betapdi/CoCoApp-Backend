package com.server.cocoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.cocoapp.dto.LocationDto;
import com.server.cocoapp.services.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LocationDto>> getAll() {
        List<LocationDto> response = locationService.getAllLocations();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{locationId}")
    public ResponseEntity<LocationDto> getLocation(@PathVariable("locationId") String locationId) {
        LocationDto response = locationService.getLocation(locationId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<LocationDto> addLocation(@RequestPart("image") MultipartFile file,
                                                    @RequestPart("location") LocationDto locationDto) throws IOException {
        LocationDto response = locationService.addLocation(file, locationDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/delete/{locationId}")
    public ResponseEntity<String> deleteLocation(@PathVariable("locationId") String locationId) throws IOException {
        String response = locationService.deleteLocation(locationId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<LocationDto> updateLocation(@RequestPart("image") MultipartFile file, 
                                                        @RequestPart("location") LocationDto locationDto) throws IOException {
        LocationDto response = locationService.updateLocation(file, locationDto);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    


    
}
