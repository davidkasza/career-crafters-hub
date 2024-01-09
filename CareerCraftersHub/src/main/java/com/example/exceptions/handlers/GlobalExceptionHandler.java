package com.example.exceptions.handlers;

import com.example.dtos.ErrorDTO;
import com.example.exceptions.*;
import com.example.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
    @ExceptionHandler(value = EmailNotVerifiedException.class)
    public ResponseEntity<Object> handleEmailNotVerifiedException(EmailNotVerifiedException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", "Invalid token: " + exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = UserAlreadyVerifiedException.class)
    public ResponseEntity<Object> handleUserAlreadyVerifiedException(UserAlreadyVerifiedException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProfilePhotoException.class)
    public ResponseEntity<Object> handleProfilePhotoException(UserNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = DefaultPhotoException.class)
    public ResponseEntity<Object> handleDefaultPhotoException(DefaultPhotoException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}