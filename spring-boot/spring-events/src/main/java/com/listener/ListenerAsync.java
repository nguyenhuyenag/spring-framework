package com.listener;

import com.event.EventAsync;
import com.event.EventBasic;
import com.util.DatetimeUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/*
    Listener Async (bất đồng bộ).
 */
@Component
public class ListenerAsync {

    @Async
    @EventListener
    public void eventListenerAsync(EventAsync event) {
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", method=eventListenerAsync"
                + ",\t\t\tReceived message: " + event.getMessage() + "\n");
    }

}
