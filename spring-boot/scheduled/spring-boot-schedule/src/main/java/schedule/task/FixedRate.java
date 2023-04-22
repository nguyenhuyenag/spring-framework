package schedule.task;

import org.springframework.stereotype.Component;

@Component
public class FixedRate {

	/**
	 * - fixedRate thì giống với fixedDelay, tuy nhiên cứ sau khoảng thời gian
	 * fixedRate thì nó chạy tiếp 1 lần nữa mà không cần quan tâm lần chạy trước đã
	 * hoàn thành chưa
	 */
	// @Scheduled(fixedRateString = "${time.repeate}")
	// public void scheduleFixedDelayTask() throws InterruptedException {
	// System.out.println("JobFixedRate - " + TimeUtils.format(new Date()));
	// }

	// @Scheduled(fixedRate = 2000)
	// public void scheduleFixedRateTask() throws InterruptedException {
	// System.out.println("JobFixedRate - " + new Date());
	// }

}
