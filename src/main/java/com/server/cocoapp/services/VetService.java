package com.server.cocoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.auth.entities.User;
import com.server.cocoapp.auth.repositories.UserRepository;
import com.server.cocoapp.classes.Appointment;
import com.server.cocoapp.dto.VetDto;
import com.server.cocoapp.entities.Vet;
import com.server.cocoapp.exceptions.UserNotFoundException;
import com.server.cocoapp.exceptions.VetNotFoundException;
import com.server.cocoapp.repositories.LocationRepository;
import com.server.cocoapp.repositories.VetRepository;

import java.io.IOException;

@Service
public class VetService {
    private final VetRepository vetRepository;
    private final FileService fileService;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.static}")
    private String path;
    
    public VetService(VetRepository vetRepository, FileService fileService, LocationRepository locationRepository, UserRepository userRepository) {
        this.vetRepository = vetRepository;
        this.fileService = fileService;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
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

        
        if (!file.isEmpty()) {
            String fileName = fileService.uploadFile(path, file);
            vet.getLocation().setImageName(fileName);
        }
        
        locationRepository.save(vet.getLocation());
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

        if (!file.isEmpty()) {
            if (vet.getLocation().getImageName() != null) {
                fileService.deleteFile(path, vet.getLocation().getImageName());
            }

            String fileName = fileService.uploadFile(path, file);
            vet.getLocation().setImageName(fileName);
        }

        locationRepository.save(vet.getLocation());
        vetRepository.save(vet);

        VetDto response = new VetDto();
        response.update(vet);

        return response;
    }

    public String addAppointment(String username, Appointment appointment) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Vet vet = vetRepository.findById(appointment.getVetId()).orElseThrow(() -> new VetNotFoundException("Vet not found!"));

        appointment.setUserId(user.getUserId());
        vet.getAppointments().add(appointment);
        user.getAppointments().add(appointment);

        vetRepository.save(vet);
        userRepository.save(user);

        return "Appointment added with id: " + appointment.getId();
    }

    public String deleteAppointment(String username, Appointment appointment) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Vet vet = vetRepository.findById(appointment.getVetId()).orElseThrow(() -> new VetNotFoundException("Vet not found!"));

        user.getAppointments().removeIf(obj -> (obj.getId().equals(appointment.getId())));
        vet.getAppointments().removeIf(obj -> (obj.getId().equals(appointment.getId())));

        userRepository.save(user);
        vetRepository.save(vet);

        return "Appointment deleted with id: " + appointment.getId();
    }

    public List<Appointment> getListAppointments(String vetId) {
        Vet vet = vetRepository.findById(vetId).orElseThrow(() -> new VetNotFoundException("Vet not found!"));
        return vet.getAppointments();
    }
}
