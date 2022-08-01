package quartz.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import quartz.jobs.Job1;
import quartz.jobs.Job2;

@Configuration
public class QuartzRegisterJobs {

	@Bean(name = "jobOne")
	public JobDetailFactoryBean jobOne() {
		return QuartzConfig.createJobDetail(Job1.class);
	}

	@Bean(name = "triggerOfJobOne")
	public SimpleTriggerFactoryBean triggerOfJobOne(@Qualifier("jobOne") JobDetail job) {
		return QuartzConfig.createTrigger(job, 2);
	}

	@Bean(name = "jobTwo")
	public JobDetailFactoryBean jobTwo() {
		return QuartzConfig.createJobDetail(Job2.class);
	}

	@Bean(name = "triggerOfJobTwo")
	public SimpleTriggerFactoryBean triggerOfJobTwo(@Qualifier("jobTwo") JobDetail job) {
		return QuartzConfig.createTrigger(job, 4);
	}

}
