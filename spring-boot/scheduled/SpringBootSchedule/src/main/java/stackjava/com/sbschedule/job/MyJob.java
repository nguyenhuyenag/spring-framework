package stackjava.com.sbschedule.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJob {

	//@Value("${time.repeate}")
	//final long time = 0;
	
	@Scheduled(fixedDelayString  = "${time.repeate}")
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("Task1 - " + new Date());
	}

//	@Scheduled(fixedRate = 2000)
//	public void scheduleFixedRateTask() throws InterruptedException {
//		System.out.println("Task2 - " + new Date());
//	}
//
//	@Scheduled(cron = "*/3 * * * * *")
//	public void scheduleTaskUsingCronExpression() throws InterruptedException {
//		System.out.println("Task3 - " + new Date());
//	}

}
