package com.example.services;

import com.example.dtos.CommentDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentService {
    CommentDTO publishComment(HttpServletRequest request, CommentDTO commentDTO, Long id);
}
