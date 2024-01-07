package com.example.controllers;

import com.example.services.ProfilePhotoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ProfilePhotoController {
    private ProfilePhotoService profilePhotoService;

    public ProfilePhotoController(ProfilePhotoService profilePhotoService) {
        this.profilePhotoService = profilePhotoService;
    }

    @PostMapping("/set-profile-photo")
    public ResponseEntity<String> uploadProfilePhoto(HttpServletRequest request, @RequestParam("file") MultipartFile profilePhoto) {
        if (profilePhoto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a file");
        }

        profilePhotoService.convertPhotoToByte(request, profilePhoto);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
    }

    @GetMapping("/get-profile-photo")
    public ResponseEntity<?> getProfilePhoto(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(profilePhotoService.convertBytesToPhoto(request));
    }
}
