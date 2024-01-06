package com.example.controllers;

import com.example.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmailAddress(@RequestParam String token) {
        userService.verifyUser(token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Your email has been verified");
    }
}
