package com.example.services;

import com.example.dtos.CommentDTO;
import com.example.dtos.PostDTO;
import com.example.dtos.RegisteredUserDTO;
import com.example.dtos.UserDTO;
import com.example.entities.Comment;
import com.example.entities.Post;
import com.example.entities.User;

public interface MapperService {
    UserDTO convertUserToUserDTO(User user);

    User convertUserDTOToUser(UserDTO userDTO);

    RegisteredUserDTO convertUserToRegisteredUserDTO(User user);

    Post convertPostDTOToPost(PostDTO postDTO);

    Comment convertCommentDTOToComment(CommentDTO commentDTO);
}
