package quartz.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import quartz.jobs.Job1;
import quartz.jobs.Job2;

@Configuration
public class QuartzRegisterJobs {

//	@Bean(name = "job_1st")
//	public JobDetailFactoryBean jobOne() {
//		return QuartzConfig.createJobDetail(Job1.class);
//	}
//
//	@Bean(name = "trigger_job_1st")
//	public SimpleTriggerFactoryBean triggerOfJobOne(@Qualifier("job_1st") JobDetail job) {
//		return QuartzConfig.createTrigger(job, 5);
//	}

	@Bean
	public void createJob() throws SchedulerException {
		Map<Class<? extends Job>, Integer> map = new HashMap<>();
		map.put(Job1.class, 3);
		map.put(Job2.class, 7);
		Map<JobDetail, Set<? extends Trigger>> triggersAndJobs = new HashMap<>();
		AtomicInteger index = new AtomicInteger();
		map.forEach((k, v) -> {
			String group = "group" + index.incrementAndGet();
			// Create a job
			JobDetail job = JobBuilder.newJob(k) // Set Job
					.withIdentity(k.getName(), group) //
					.build();
			// Create a trigger
			Trigger trigger = TriggerBuilder.newTrigger() //
					.withIdentity("Trigger" + index.get(), group) //
					.withSchedule(SimpleScheduleBuilder.simpleSchedule() //
							.withIntervalInSeconds(v) // Set interval
							.repeatForever()) //
					.build();
			triggersAndJobs.put(job, new HashSet<>(Arrays.asList(trigger)));
		});

		// schedule the job with the trigger
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJobs(triggersAndJobs, true);
	}

}
