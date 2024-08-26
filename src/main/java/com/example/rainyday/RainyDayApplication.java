package com.example.rainyday;

import com.example.rainyday.listener.MyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

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


}