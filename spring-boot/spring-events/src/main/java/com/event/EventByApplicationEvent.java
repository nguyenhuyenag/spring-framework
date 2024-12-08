package com.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/*
    Tạo event bằng cách extends ApplicationEvent.
 */
@Getter
public class EventByApplicationEvent extends ApplicationEvent {

    private final String message;

    public EventByApplicationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
