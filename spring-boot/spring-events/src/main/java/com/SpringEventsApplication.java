package com;

import com.publisher.PublisherEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringEventsApplication implements CommandLineRunner {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SpringEventsApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringEventsApplication.class, args);
    }

    @Autowired
    PublisherEvent customSpringEventPublisher;

    public void doSend() throws InterruptedException {
        int i = 0;
        while (true) {
            customSpringEventPublisher.publishCustomEvent(i + "");
            i++;
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        doSend();
    }

}
