package com.example.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ProfilePhotoService {
    void convertPhotoToByte(HttpServletRequest request, MultipartFile profilePhoto);

    byte[] convertBytesToPhoto(HttpServletRequest request);
}
