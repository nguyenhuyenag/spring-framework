package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching   // ← BẮT BUỘC phải có annotation này
@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
