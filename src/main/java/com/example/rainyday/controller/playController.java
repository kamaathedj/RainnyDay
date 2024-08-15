package com.example.rainyday.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class playController {

    static final String url = "Http://idntlikethisapi.com/api/jokes";

    playController(){
        System.out.println("am ready for the play");
    }

    @GetMapping("/api")
    @Secured(value = "ROLE_ADMIN")
    public String play(){
        return url;
    }
}
