package com.runner;

import com.publisher.PublisherEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final PublisherEvent publisherEvent;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int i = 0;
        while (i < 20) {
            publisherEvent.publishCustomEvent("My message " + i++);
            TimeUnit.SECONDS.sleep(4);
        }
    }

}
