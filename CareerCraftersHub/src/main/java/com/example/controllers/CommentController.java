package com.example.controllers;

import com.example.dtos.CommentDTO;
import com.example.dtos.ErrorDTO;
import com.example.services.CommentService;
import com.example.services.GeneralUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{id}/publish-comment")
    public ResponseEntity<?> publishComment(HttpServletRequest request, @PathVariable String id, @RequestBody CommentDTO commentDTO) {
        if (!GeneralUtility.isValidLongNumber(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("error", "The given id is not a valid number!"));
        }

        String invalidParameter = "";

        if (GeneralUtility.isEmptyOrNull(commentDTO.getText())) {
            invalidParameter = "Comment text";
        }

        if (invalidParameter.length() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("error", invalidParameter + " is required!"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.publishComment(request, commentDTO, Long.valueOf(id)));
    }
}
