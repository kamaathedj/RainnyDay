package com.example.rainyday;

import com.example.rainyday.listener.MyEvent;
import com.example.rainyday.model.User;
import com.example.rainyday.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RainyDayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainyDayApplication.class, args);
    }

    @Bean
    ApplicationListener<MyEvent> readyListener(){
        return event -> {
            System.out.println(event.getMessage());
        };
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyListener2(UserRepository userRepository, PasswordEncoder encoder){
        return event -> {
            userRepository.save(new User("kamaa", encoder.encode("1234"), true));
            userRepository.save(new User("david", encoder.encode("1234"), false));
        };
    }


}