package com.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreatedEvent extends ApplicationEvent {

    private final String message;

    public UserCreatedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
