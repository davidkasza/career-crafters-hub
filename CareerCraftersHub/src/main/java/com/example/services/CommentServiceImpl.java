package com.example.services;

import com.example.dtos.CommentDTO;
import com.example.entities.Comment;
import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.CommentRepository;
import com.example.repositories.PostRepository;
import com.example.security.CareerCraftersHubUserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CommentServiceImpl implements CommentService {
    private CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService;

    private MapperService mapperService;

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    public CommentServiceImpl(CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService, MapperService mapperService, PostRepository postRepository, CommentRepository commentRepository) {
        this.careerCraftersHubUserDetailsService = careerCraftersHubUserDetailsService;
        this.mapperService = mapperService;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDTO publishComment(HttpServletRequest request, CommentDTO commentDTO, Long id) {
        User user = careerCraftersHubUserDetailsService.getUserByUsernameFromRequest(request);
        Post post = postRepository.findById(id).get();
        Comment comment = mapperService.convertCommentDTOToComment(commentDTO);

        comment.setPost(post);
        comment.setUser(user);

        commentRepository.save(comment);

        return commentDTO;
    }
}
