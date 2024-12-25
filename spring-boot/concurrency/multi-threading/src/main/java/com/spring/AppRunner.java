package com.spring;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

//    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    private final AsyncMethod gitHubLookupService;

    public AppRunner(AsyncMethod gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    public void test1() throws InterruptedException, ExecutionException {
        // Start the clock
        long start = System.currentTimeMillis();
        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
        // Wait until they are all done
        CompletableFuture.allOf(page1, page2, page3).join();
        // Print results, including elapsed time
        log.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }

    public void test2() {
        // Start the clock
        long start = System.currentTimeMillis();
        // Kick of multiple, asynchronous lookups
        User page1 = gitHubLookupService.findUserWithoutAsync("PivotalSoftware");
        User page2 = gitHubLookupService.findUserWithoutAsync("CloudFoundry");
        User page3 = gitHubLookupService.findUserWithoutAsync("Spring-Projects");
        // Print results, including elapsed time
        log.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        log.info("--> " + page1);
        log.info("--> " + page2);
        log.info("--> " + page3);
    }

    @Override
    public void run(String... args) throws Exception {
        test1();
        test2();
    }
}
