package com.listener;

import com.util.DatetimeUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.event.CustomEvent;

@Component
public class ListenerUsingEventListener {

    // @Async
    @EventListener
    public void handleReturnedEvent(CustomEvent event) {
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", class=" + this.getClass().getSimpleName()
                + ", \tReceived message: " + event.getMessage() + "\n");
    }

}
