package com.schedule.jobs;

import java.util.Date;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "scheduling.enabled", havingValue = "true", matchIfMissing = true)
public class FixedDelay {

	@Scheduled(fixedDelayString = "${time.repeate}")
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("Fixed delay task - " + new Date());
	}

}
