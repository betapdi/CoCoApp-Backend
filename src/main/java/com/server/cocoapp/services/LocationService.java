package com.server.cocoapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.dto.LocationDto;
import com.server.cocoapp.entities.Location;
import com.server.cocoapp.exceptions.LocationNotFoundException;
import com.server.cocoapp.repositories.LocationRepository;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final FileService fileService;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.static}")
    private String path;

    public LocationService(LocationRepository locationRepository, FileService fileService) {
        this.locationRepository = locationRepository;
        this.fileService = fileService;
    }

    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationDto> dtos = new ArrayList<>();
        
        for (Location location : locations) {
            LocationDto dto = new LocationDto();
            dto.update(location);

            if (location.getImageName() != null) dto.setImageUrl(baseUrl + "/file/" + location.getImageName());

            dtos.add(dto);
        }

        return dtos;
    }

    public LocationDto getLocation(String locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
        
        LocationDto locationDto = new LocationDto();
        locationDto.update(location);

        if (location.getImageName() != null) locationDto.setImageUrl(baseUrl + "/file/" + location.getImageName());

        return locationDto;
    }

    public LocationDto addLocation(MultipartFile file, LocationDto locationDto) throws IOException {
        Location newLocation = new Location();
        
        newLocation.update(locationDto);

        if (file != null) {
            String fileName = fileService.uploadFile(path, file);
            newLocation.setImageName(fileName);
        }

        locationRepository.save(newLocation);

        LocationDto dto = new LocationDto();
        dto.update(newLocation);
        if (newLocation.getImageName() != null) dto.setImageUrl(baseUrl + "/file/" + newLocation.getImageName());

        return dto;
    }

    public String deleteLocation(String locationId) throws IOException {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
        
        if (location.getImageName() != null) {
            fileService.deleteFile(path, location.getImageName());
        }

        locationRepository.delete(location);

        return "Location deleted with id: " + location.getId();
    }

    public LocationDto updateLocation(MultipartFile file, LocationDto locationDto) throws IOException {
        Location location = locationRepository.findById(locationDto.getId()).orElseThrow(() -> new LocationNotFoundException("Location not found!"));
        
        location.update(locationDto);

        if (file != null) {
            if (location.getImageName() != null) {
                fileService.deleteFile(path, location.getImageName());
            }

            String fileName = fileService.uploadFile(path, file);
            location.setImageName(fileName);
        }

        locationRepository.save(location);

        LocationDto dto = new LocationDto();
        dto.update(location);
        if (location.getImageName() != null) dto.setImageUrl(baseUrl + "/file/" + location.getImageName());

        return dto;
    }
}
