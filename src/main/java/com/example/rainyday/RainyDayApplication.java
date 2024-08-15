package com.example.rainyday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RainyDayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainyDayApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyListener(){
        return event -> {
            System.out.println("reADY machine");
        };
    }


}