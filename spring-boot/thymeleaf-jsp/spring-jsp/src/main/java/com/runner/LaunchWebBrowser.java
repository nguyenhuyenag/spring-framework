package com.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

@Component
public class LaunchWebBrowser {

    @Value("${server.port}")
    private int port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        String url = "http://localhost:" + port + contextPath;
        System.out.println("Application started at: " + url);
    }

}
