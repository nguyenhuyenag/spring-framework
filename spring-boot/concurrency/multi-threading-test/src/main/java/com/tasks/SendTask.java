package com.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendTask {

    // Chạy vào giây thứ 10 của mỗi phút
    @Scheduled(cron = "10 * * ? * *")
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + new Date());
    }

}
