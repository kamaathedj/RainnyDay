package com.example.rainyday.controller;


import com.example.rainyday.listener.EventDetails;
import com.example.rainyday.listener.MyEvent;
import com.example.rainyday.model.User;
import com.example.rainyday.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
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
    public User createUser(@RequestBody User user){
        EventDetails details = new EventDetails("user creation", "New user created");
        MyEvent event = new MyEvent(this,details);
        var createdUser =  userService.createNewUser(user);
        publisher.publishEvent(event);
        return createdUser;

    }
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
