package com.example.rainyday.listener;

import org.springframework.context.ApplicationEvent;


public class MyEvent extends ApplicationEvent {
    private final EventDetails message;

    public MyEvent(Object source, EventDetails message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message.description;

    }
}
