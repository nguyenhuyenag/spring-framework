package com.schedule.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.schedule.jobs.PutHoaDon;
import com.util.ConfigReader;

@Configuration
public class QuartzRegisterJobs {

	@Bean(name = "jobPutHoaDon")
	public JobDetailFactoryBean jobPutHoaDon() {
		return QuartzConfig.createJobDetail(PutHoaDon.class);
	}

	@Bean(name = "triggerPutHoaDon")
	public SimpleTriggerFactoryBean triggerPutHoaDon(@Qualifier("jobPutHoaDon") JobDetail job) {
		return QuartzConfig.createTrigger(job, ConfigReader.JOB_TIME_PUT_HOADON);
	}

}
