package com.tasks;

import com.entity.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class SendTask {

    // Chạy vào giây thứ 10 của mỗi phút
    @Scheduled(cron = "10 * * ? * *")
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + data.size());
    }

}
