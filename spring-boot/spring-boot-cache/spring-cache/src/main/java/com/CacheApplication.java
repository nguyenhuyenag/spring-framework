package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheApplication extends SpringBootServletInitializer implements CommandLineRunner {

    // JAR
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    // WAR
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CacheApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
