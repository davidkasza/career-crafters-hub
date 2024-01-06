package com.example.services;

import com.example.dtos.RegisteredUserDTO;
import com.example.dtos.UserDTO;
import com.example.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {
    private ModelMapper modelMapper;

    @Autowired
    public MapperServiceImpl() {
        modelMapper = new ModelMapper();
    }

    public UserDTO convertUserToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        modelMapper.map(userDTO, user);
        return user;
    }

    public RegisteredUserDTO convertUserToRegisteredUserDTO(User user) {
        return modelMapper.map(user, RegisteredUserDTO.class);
    }
}
