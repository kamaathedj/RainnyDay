package com.example.rainyday.controller;


import com.example.rainyday.listener.EventDetails;
import com.example.rainyday.listener.MyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class playController {
    final ApplicationEventPublisher publisher;

    static final String url = "Http://idntlikethisapi.com/api/jokes";

    playController(ApplicationEventPublisher publisher){
        this.publisher = publisher;
        System.out.println("am ready for the play");
    }

    @GetMapping("/api")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String play(Principal principal){
        EventDetails details = new EventDetails("my details", "These are just details");
        MyEvent event = new MyEvent(this,details);
        publisher.publishEvent(event);
        return principal.getName();
    }
}
