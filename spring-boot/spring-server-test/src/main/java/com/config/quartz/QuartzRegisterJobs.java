package com.config.quartz;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.jobs.JokesJob;

@Configuration
public class QuartzRegisterJobs {

	@Bean(name = "jobOne")
	public JobDetailFactoryBean jobOne() {
		return QuartzConfig.createJobDetail(JokesJob.class);
	}

	@Bean(name = "triggerOfJobOne")
	public SimpleTriggerFactoryBean triggerOfJobOne(@Qualifier("jobOne") JobDetail job) {
		return QuartzConfig.createTrigger(job, 3);
	}

}
