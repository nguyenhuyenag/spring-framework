package com.config.quartz;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.jobs.JobJokes;

@Configuration
public class QuartzRegisterJobs {

	@Bean(name = "jobOne")
	public JobDetailFactoryBean jobOne() {
		return QuartzConfig.createJobDetail(JobJokes.class);
	}

	@Bean(name = "triggerOfJobOne")
	public SimpleTriggerFactoryBean triggerOfJobOne(@Qualifier("jobOne") JobDetail job) {
		return QuartzConfig.createTrigger(job, 3);
	}

}
