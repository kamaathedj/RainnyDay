package com.example.rainyday.service;


import com.example.rainyday.model.User;
import com.example.rainyday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    final
    PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getLoggedInUser(){
        return "logged in successfully as admin";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createNewUser(User user){
        User newUser = new User(user.getUsername(), encoder.encode(user.getPassword()),user.isAdmin());
        return userRepository.save(newUser) ;

    }

    @PostAuthorize("returnObject.username == authentication.name")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
