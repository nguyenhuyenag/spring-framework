package com.listener;

import com.util.DatetimeUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.event.EventByApplicationEvent;

/**
 * Nếu khai báo 2 Listener thì cả 2 sẽ cùng nhận được message.
 */
@Component
public class ListenerUsingApplicationListener implements ApplicationListener<EventByApplicationEvent> {

    @Override
    public void onApplicationEvent(EventByApplicationEvent event) {
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", method=onApplicationEvent"
                + ",\tReceived message: " + event.getMessage() + "\n");
    }

}
