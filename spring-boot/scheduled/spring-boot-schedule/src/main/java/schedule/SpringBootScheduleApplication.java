package schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SpringBootScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScheduleApplication.class, args);
	}

	/**
	 * Mặc định thread pool cho schedule task có giá trị là 1. Tức là hệ thống chỉ
	 * tạo ra duy nhất 1 thread để chạy các schedule task. Do đó bạn sẽ gặp trường
	 * hợp đến thời gian chỉ định mà task vẫn không được thực hiện vì có 1 task
	 * trước đó chưa hoàn thành kể cả với fixedRate, fixedDelay hay cron.
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10); // tăng pool size để mỗi task chạy với một thread độc lập
		return scheduler;
	}

}
