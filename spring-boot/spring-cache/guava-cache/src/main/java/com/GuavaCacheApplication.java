package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GuavaCacheApplication extends SpringBootServletInitializer implements CommandLineRunner {

    // JAR
    public static void main(String[] args) {
        SpringApplication.run(GuavaCacheApplication.class, args);
    }

    // WAR
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GuavaCacheApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
