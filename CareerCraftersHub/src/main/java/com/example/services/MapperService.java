package com.example.services;

import com.example.dtos.RegisteredUserDTO;
import com.example.dtos.UserDTO;
import com.example.entities.User;

public interface MapperService {
    UserDTO convertUserToUserDTO(User user);

    User convertUserDTOToUser(UserDTO userDTO);

    RegisteredUserDTO convertUserToRegisteredUserDTO(User user);
}
