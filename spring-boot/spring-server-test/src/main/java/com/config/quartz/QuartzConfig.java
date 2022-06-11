package com.config.quartz;

import org.apache.commons.lang3.ArrayUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private ApplicationContext applicationContext;

//	public QuartzConfig(ApplicationContext applicationContext) {
//		this.applicationContext = applicationContext;
//	}
	
	@Value("${spring.quartz.properties.org.quartz.scheduler.enabled:true}")
	private boolean enabled;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		BeanJobFactory factory = new BeanJobFactory();
		factory.setApplicationContext(applicationContext);
		return factory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger... triggers) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		// Properties properties = new Properties();
		factory.setAutoStartup(enabled);
		factory.setOverwriteExistingJobs(true);
		// schedulerFactory.setQuartzProperties(properties);
		factory.setJobFactory(springBeanJobFactory());
		factory.setWaitForJobsToCompleteOnShutdown(true);
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
		factory.setStartDelay(0L);
		factory.setJobDetail(jobDetail);
		factory.setRepeatInterval(timeRepeat * 1000); // giay
		factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		// factory.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
		return factory;
	}

}
