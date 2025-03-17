package com.runner;

import com.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("redisService.toString() = " + redisService.toString());
    }

}
