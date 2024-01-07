package com.example.exceptions;

public class DefaultPhotoException extends RuntimeException {
    public DefaultPhotoException() {
        super("Failed to load the file");
    }
}