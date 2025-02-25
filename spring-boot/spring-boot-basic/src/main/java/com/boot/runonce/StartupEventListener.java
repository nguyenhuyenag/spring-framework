package com.boot.runonce;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*
    Phương thưc chỉ chạy 1 lần
 */
@Component
public class StartupEventListener {

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("[ApplicationReadyEvent] Application is fully started. This runs once.");
    }

}
