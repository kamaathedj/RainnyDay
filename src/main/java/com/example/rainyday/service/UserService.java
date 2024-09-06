package com.example.rainyday.service;


import com.example.rainyday.model.User;
import com.example.rainyday.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getLoggedInUser(){
        return "logged in successfully as admin";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createNewUser(User user){
        return userRepository.save(user) ;

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
