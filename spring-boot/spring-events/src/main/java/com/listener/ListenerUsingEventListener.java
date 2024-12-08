package com.listener;

import com.util.DatetimeUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.event.EventBasic;

@Component
public class ListenerUsingEventListener {

    // @Async
    @EventListener
    public void eventListener(EventBasic event) {
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", method=eventListener"
                + ",\t\tReceived message: " + event.getMessage() + "\n");
    }

}
