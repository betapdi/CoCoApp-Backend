package com.server.cocoapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.cocoapp.services.FileService;

import java.io.InputStream;

import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    @Value("${project.static}")
    private String path;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
        String uploadedFileName = fileService.uploadFile(path, file);
        return new ResponseEntity<>("File uploaded: " + uploadedFileName, HttpStatus.OK);
    }
    
    @GetMapping("/{fileName}")
    public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream resourceFile = fileService.getResoureFile(path, fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);

        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
