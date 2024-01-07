package com.example.exceptions;

public class ProfilePhotoException extends RuntimeException {
    public ProfilePhotoException() {
        super("Failed to upload file");
    }
}