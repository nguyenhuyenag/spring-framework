package com.runner;

import com.model.Student;
import com.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("redisService.toString() = " + redisService.toString());

        String id = UUID.randomUUID().toString();
        Student student = new Student(id, "Java", 1995);
        redisService.add(student);

        // System.out.println("student = " + redisService.findById(id));

        redisService.findAll().forEach(System.out::println);

        System.out.println("student = " + redisService.getAll());
    }

}
