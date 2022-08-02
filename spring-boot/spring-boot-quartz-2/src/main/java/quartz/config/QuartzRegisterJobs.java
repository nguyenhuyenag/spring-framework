package quartz.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import quartz.jobs.Job1;
import quartz.jobs.Job2;

@Configuration
public class QuartzRegisterJobs {

	@Bean(name = "jobOne")
	public JobDetail jobOne() {
		return QuartzConfig.createJobDetail(Job1.class);
	}

	@Bean(name = "triggerOfJobOne")
	public Trigger triggerOfJobOne(@Qualifier("jobOne") JobDetail job) {
		return QuartzConfig.createTrigger(job, 2);
	}

	@Bean(name = "jobTwo")
	public JobDetail jobTwo() {
		return QuartzConfig.createJobDetail(Job2.class);
	}

	@Bean(name = "triggerOfJobTwo")
	public Trigger triggerOfJobTwo(@Qualifier("jobTwo") JobDetail job) {
		return QuartzConfig.createTrigger(job, 4);
	}

}
