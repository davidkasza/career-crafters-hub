package com.example.services;

import com.example.dtos.PostDTO;
import com.example.entities.Post;
import com.example.repositories.PostRepository;
import com.example.security.CareerCraftersHubUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    private MapperService mapperService;

    private CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService;

    public PostServiceImpl(PostRepository postRepository, MapperService mapperService, CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService) {
        this.postRepository = postRepository;
        this.mapperService = mapperService;
        this.careerCraftersHubUserDetailsService = careerCraftersHubUserDetailsService;
    }

    public PostDTO addNewPost(HttpServletRequest request, PostDTO postDTO) {
        Post post = mapperService.convertPostDTOToPost(postDTO);
        post.setUser(careerCraftersHubUserDetailsService.getUserByUsernameFromRequest(request));
        postRepository.save(post);

        return postDTO;
    }
}
