package com.example.services;

import com.example.dtos.RegisteredUserDTO;
import com.example.dtos.UserDTO;
import com.example.entities.User;
import com.example.exceptions.UserAlreadyVerifiedException;
import com.example.repositories.UserRepository;
import com.example.security.CareerCraftersHubUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    private MapperService mapperService;

    private CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder, MapperService mapperService, CareerCraftersHubUserDetailsService careerCraftersHubUserDetailsService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.mapperService = mapperService;
        this.careerCraftersHubUserDetailsService = careerCraftersHubUserDetailsService;
    }

    public RegisteredUserDTO addNewUser(UserDTO userDTO) {
        User user = mapperService.convertUserDTOToUser(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        saveUser(user);

        return mapperService.convertUserToRegisteredUserDTO(user);
    }

    public boolean isUserRegistered(String desiredUsername) {
        return !userRepository.findUserByUsername(desiredUsername).isEmpty();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void saveVerificationTokenForUser(UserDTO userDTO, String token) {
        User user = findUserByUsername(userDTO.getUsername()).get();
        user.setVerificationToken(token);
        userRepository.save(user);
    }

    public void verifyUser(String verificationToken) {
        User user = careerCraftersHubUserDetailsService.getUserByEmailVerificationToken(verificationToken);
        if (user.getVerified()) {
            throw new UserAlreadyVerifiedException();
        } else {
            user.setVerified(true);
            userRepository.save(user);
        }
    }
}
