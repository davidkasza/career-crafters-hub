package com.example.services;

import com.example.entities.User;
import com.example.exceptions.DefaultPhotoException;
import com.example.exceptions.ProfilePhotoException;
import com.example.repositories.UserRepository;
import com.example.security.CareerCraftersHubUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class ProfilePhotoServiceImpl implements ProfilePhotoService {
    private CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService;

    private UserRepository userRepository;

    public ProfilePhotoServiceImpl(CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService, UserRepository userRepository) {
        this.careerCraftersHubUserDetailsService = careerCraftersHubUserDetailsService;
        this.userRepository = userRepository;
    }

    public void convertPhotoToByte(HttpServletRequest request, MultipartFile profilePhoto) {
        byte[] profilePhotoBytes = new byte[(int) profilePhoto.getSize()];
        int bytesRead;

        try {
            bytesRead = profilePhoto.getInputStream().read(profilePhotoBytes);
        } catch (IOException e) {
            throw new ProfilePhotoException();
        }

        User user = careerCraftersHubUserDetailsService.getUserByUsernameFromRequest(request);

        user.setProfilePhoto(profilePhotoBytes);
        userRepository.save(user);
    }

    public byte[] convertBytesToPhoto(HttpServletRequest request) {
        User user = careerCraftersHubUserDetailsService.getUserByUsernameFromRequest(request);
        byte[] profilePhotoBytes = user.getProfilePhoto();

        if (profilePhotoBytes == null || profilePhotoBytes.length == 0) {
            Resource resource = new ClassPathResource("default_profile_photo.jpg");
            try {
                profilePhotoBytes = Files.readAllBytes(resource.getFile().toPath());
            } catch (IOException e) {
                throw new DefaultPhotoException();
            }
        }

        return profilePhotoBytes;
    }
}
