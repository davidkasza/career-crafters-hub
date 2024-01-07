package com.example.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePhotoService {
    void convertPhotoToByte(HttpServletRequest request, MultipartFile profilePhoto);

    byte[] convertBytesToPhoto(HttpServletRequest request);
}
