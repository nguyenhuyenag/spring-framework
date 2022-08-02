package schedule.job;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobFixedRate {

	/**
	 * - fixedRate thì giống với fixedDelay, tuy nhiên cứ sau khoảng thời gian
	 * fixedRate thì nó chạy tiếp 1 lần nữa mà không cần quan tâm lần chạy trước đã
	 * hoàn thành chưa
	 */
	@Scheduled(fixedRateString = "${time.repeate}")
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("JobFixedRate_Task1 - " + new Date());
	}

	@Scheduled(fixedRate = 2000)
	public void scheduleFixedRateTask() throws InterruptedException {
		System.out.println("JobFixedRate_Task2 - " + new Date());
	}

}
