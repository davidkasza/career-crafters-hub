package com.example.controllers;

import com.example.dtos.ErrorDTO;
import com.example.dtos.PostDTO;
import com.example.services.GeneralUtility;
import com.example.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/publish-post")
    public ResponseEntity<?> publishNewPost(HttpServletRequest request, @RequestBody PostDTO postDTO) {
        String invalidParameter = "";

        if (GeneralUtility.isEmptyOrNull(postDTO.getText())) {
            invalidParameter = "Post text";
        }

        if (invalidParameter.length() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("error", invalidParameter + " is required!"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addNewPost(request, postDTO));
    }
}
