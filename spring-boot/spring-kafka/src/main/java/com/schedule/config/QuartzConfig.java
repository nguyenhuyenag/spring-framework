package com.schedule.config;

import org.apache.commons.lang3.ArrayUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {

	private static final Logger LOG = LoggerFactory.getLogger(QuartzConfig.class);

	private ApplicationContext applicationContext;

	public QuartzConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		BeanJobFactory jobFactory = new BeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger... triggers) {
		// Properties properties = new Properties();
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setAutoStartup(true);
		factory.setOverwriteExistingJobs(true);
		factory.setWaitForJobsToCompleteOnShutdown(true);
		// factory.setQuartzProperties(properties);
		factory.setJobFactory(springBeanJobFactory());
		if (ArrayUtils.isNotEmpty(triggers)) {
			factory.setTriggers(triggers);
		}
		return factory;
	}
	
	public static JobDetailFactoryBean createJobDetail(Class<? extends Job> jobClass) {
		LOG.debug("createJobDetail(jobClass={})", jobClass.getName());
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		factoryBean.setDurability(true);
		return factoryBean;
	}

	public static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long timeRepeat) {
		SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
		factory.setJobDetail(jobDetail);
		factory.setStartDelay(0L);
		factory.setRepeatInterval(timeRepeat * 1000); // giay
		factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		// factory.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
		return factory;
	}
	
}
