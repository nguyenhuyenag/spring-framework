package com.schedule.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.schedule.jobs.JobInsertData;
import com.schedule.jobs.JobPutData;
import com.util.ConfigReader;

@Configuration
public class QuartzRegisterJobs {

	@Bean(name = "theJobPut")
	public JobDetailFactoryBean theJobPut() {
		return QuartzConfig.createJobDetail(JobPutData.class);
	}

	@Bean(name = "triggerPut")
	public SimpleTriggerFactoryBean triggerPut(@Qualifier("theJobPut") JobDetail job) {
		return QuartzConfig.createTrigger(job, ConfigReader.JOB_TIME_RECALL);
	}

	@Bean(name = "theJobInsert")
	public JobDetailFactoryBean theJobInsert() {
		return QuartzConfig.createJobDetail(JobInsertData.class);
	}

	@Bean(name = "triggerInsert")
	public SimpleTriggerFactoryBean triggerInsert(@Qualifier("theJobInsert") JobDetail job) {
		return QuartzConfig.createTrigger(job, ConfigReader.JOB_TIME_RECALL);
	}

}
