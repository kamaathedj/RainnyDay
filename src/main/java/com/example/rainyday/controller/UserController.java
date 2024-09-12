package com.example.rainyday.controller;


import com.example.rainyday.listener.EventDetails;
import com.example.rainyday.listener.MyEvent;
import com.example.rainyday.model.User;
import com.example.rainyday.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {
    final ApplicationEventPublisher publisher;
    final UserService userService;

    static final String url = "Http://idntlikethisapi.com/api/jokes";

    UserController(ApplicationEventPublisher publisher, UserService userService){
        this.publisher = publisher;
        this.userService = userService;
        System.out.println("am ready for the play");
    }

    @GetMapping("/")
    @Transactional
    public String play(Principal principal){
        EventDetails details = new EventDetails("my details", "logged in as "+principal.getName());
        MyEvent event = new MyEvent(this,details);
        publisher.publishEvent(event);
        return userService.getLoggedInUser();
    }

    @PostMapping("/create/user")
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody User user){
        EventDetails details = new EventDetails("user creation", "New user created");
        MyEvent event = new MyEvent(this,details);
        var createdUser =  userService.createNewUser(user);
        publisher.publishEvent(event);

        return ResponseEntity.status(201).body(createdUser);

    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        System.out.println("getUserById");
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}
