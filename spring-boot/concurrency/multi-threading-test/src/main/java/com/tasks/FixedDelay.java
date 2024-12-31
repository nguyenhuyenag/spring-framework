package com.tasks;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class FixedDelay {

    private static List<AbstractMap.SimpleEntry<String, Long>> data = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 1_000_000; i++) {
            data.add(new AbstractMap.SimpleEntry<>(UUID.randomUUID().toString(), System.currentTimeMillis()));
        }
    }

    // Chạy vào giây thứ 10 của mỗi phút
    @Scheduled(cron = "10 * * ? * *")
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + data.size());
    }

}
