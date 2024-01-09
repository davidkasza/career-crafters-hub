package com.example.services;

import com.example.dtos.PostDTO;

import javax.servlet.http.HttpServletRequest;

public interface PostService {
    PostDTO addNewPost(HttpServletRequest request, PostDTO postDTO);
}
