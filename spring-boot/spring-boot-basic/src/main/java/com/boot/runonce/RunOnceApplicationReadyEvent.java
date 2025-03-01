package com.boot.runonce;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

/*
    - Phương thưc chỉ chạy 1 lần

    - ApplicationReadyEvent sẽ chạy cuối cùng, thích hợp cho
    việc sử dụng các biến ConfigReader.CHILKAT_LICENSE_KEY
 */
@Component
public class RunOnceApplicationReadyEvent {

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println(Instant.now() + "[ApplicationReadyEvent] Application is fully started. This runs once.");
    }

}
