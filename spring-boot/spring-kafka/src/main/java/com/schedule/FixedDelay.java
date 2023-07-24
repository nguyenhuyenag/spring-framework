package com.schedule;

import java.util.Date;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "scheduled.enabled", havingValue = "true", matchIfMissing = true)
public class FixedDelay {

	@Scheduled(fixedDelayString = "${scheduled.repeate}")
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("Fixed delay task - " + new Date());
	}

}
