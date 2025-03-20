package com.runner;

import com.model.Student;
import com.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        // System.out.println("redisService.toString() = " + redisService.toString());
        while (true) {
            // String id = UUID.randomUUID().toString();
            // Student student = new Student(id, RandomStringUtils.randomAlphabetic(5).toUpperCase(), 1995);
            // redisService.add(student);
            // System.out.println("student = " + redisService.findById(id));
            redisService.findAll().forEach(System.out::println);
            // System.out.println("student = " + redisService.getAll());
            TimeUnit.SECONDS.sleep(5);
            System.out.println();
        }
    }

}
