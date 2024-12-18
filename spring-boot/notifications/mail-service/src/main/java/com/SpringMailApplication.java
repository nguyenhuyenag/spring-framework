package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringMailApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
