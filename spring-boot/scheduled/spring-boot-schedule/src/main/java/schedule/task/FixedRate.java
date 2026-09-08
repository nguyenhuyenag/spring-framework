package schedule.task;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FixedRate {

	/*-
		- fixedRate thì giống với fixedDelay, tuy nhiên cứ sau khoảng thời gian
		fixedRate thì nó chạy tiếp 1 lần nữa mà không cần quan tâm lần chạy trước đã
		hoàn thành chưa
	 */
    @Scheduled(
            initialDelay = 0, // Chạy ngay khi ứng dụng được khởi động
            fixedRateString = "${time.repeate}"
    )
    public void scheduleFixedDelayTask() throws InterruptedException {
        System.out.println("JobFixedRate - " + TimeUtils.format(new Date()));
    }

	// @Scheduled(fixedRate = 2000)
	// public void scheduleFixedRateTask() throws InterruptedException {
	// System.out.println("JobFixedRate - " + new Date());
	// }

}
