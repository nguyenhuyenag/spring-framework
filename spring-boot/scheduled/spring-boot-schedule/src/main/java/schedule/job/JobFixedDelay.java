package schedule.job;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobFixedDelay {

	/**
	 * - Với fixedDelay thì chỉ khi nào task trước đó thực hiện xong thì nó mới chạy
	 * tiếp task đó lại lần nữa. Ví dụ sau 1 giây mà method scheduleFixedDelayTask
	 * chưa chạy xong thì nó sẽ chờ cho tới khi nào xong mới chạy lại lần tiếp theo
	 */
	@Scheduled(fixedDelayString = "${time.repeate}")
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("JobFixedDelay_Task1 - " + new Date());
	}

	@Scheduled(fixedDelay = 2000)
	public void scheduleFixedRateTask() throws InterruptedException {
		System.out.println("JobFixedDelay_Task2 - " + new Date());
	}

	//	@Scheduled(cron = "*/3 * * * * *")
	//	public void scheduleTaskUsingCronExpression() throws InterruptedException {
	//		System.out.println("Task3 - " + new Date());
	//	}

}
