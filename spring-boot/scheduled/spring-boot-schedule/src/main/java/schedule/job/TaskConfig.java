package schedule.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskConfig {

	/*-
	 * - Running Tasks in Parallel: Mặc định thread pool cho schedule task có giá trị
	 * là 1. Tức là hệ thống chỉ tạo ra duy nhất 1 thread để chạy các schedule task.
	 * Do đó chúng ta sẽ gặp trường hợp đến thời gian chỉ định mà task vẫn không
	 * được thực hiện vì có 1 task trước đó chưa hoàn thành kể cả với fixedRate,
	 * fixedDelay hay cron.
	 * 
	 * - If using spring boot
	 * 
	 * 		spring.task.scheduling.pool.size=5
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(5); // tăng pool size để mỗi task chạy với một thread độc lập
		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		return threadPoolTaskScheduler;
	}

}
