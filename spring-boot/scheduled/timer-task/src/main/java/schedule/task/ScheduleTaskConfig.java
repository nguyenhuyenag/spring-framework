package schedule.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduleTaskConfig {

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10);
		scheduler.setThreadNamePrefix("TaskScheduler-");
		scheduler.initialize();
		return scheduler;
	}

	@Bean
	public Runnable myTask1() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("Task 1 executed at " + TimeUtils.now());
			}
		};
	}
	
	@Bean
	public Runnable myTask2() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("Task 2 executed at " + TimeUtils.now());
			}
		};
	}
	
	@Bean
	public Runnable myTask3() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("Task 3 executed at " + TimeUtils.now());
			}
		};
	}

	@Bean
	public void scheduleTask() {
		Map<Runnable, Long> tasks = new HashMap<>();
        tasks.put(myTask1(), 2000L);
        tasks.put(myTask2(), 5000L);
        tasks.put(myTask3(), 7000L);
        
        tasks.forEach((k, v) -> {
        	taskScheduler().scheduleAtFixedRate(k, v);
        });
	}

}
