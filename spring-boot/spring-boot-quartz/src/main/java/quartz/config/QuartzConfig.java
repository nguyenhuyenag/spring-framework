package quartz.config;

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

	// @Autowired
	// private ApplicationContext applicationContext;

	//	public QuartzConfig(ApplicationContext applicationContext) {
	//		this.applicationContext = applicationContext;
	//	}

	@Value("${spring.quartz.properties.org.quartz.scheduler.enabled:true}")
	private boolean enabled;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		// BeanJobFactory factory = new BeanJobFactory();
		// factory.setApplicationContext(applicationContext);
		// return factory;
		return new SpringBeanJobFactory();
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger... triggers) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		// Properties properties = new Properties();
		// schedulerFactory.setQuartzProperties(properties);
		factory.setAutoStartup(enabled);
		factory.setOverwriteExistingJobs(true);
		factory.setJobFactory(springBeanJobFactory());
		factory.setWaitForJobsToCompleteOnShutdown(true);
		for (Trigger t : triggers) {
			LOG.info("JobDetail={}, Trigger={}", t.getJobKey().getName(), t.getKey().getName());
		}
		if (ArrayUtils.isNotEmpty(triggers)) {
			factory.setTriggers(triggers);
		}
		return factory;
	}

	public static JobDetailFactoryBean createJobDetail(Class<? extends Job> classJob) {
		LOG.info("createJobDetail(jobClass={})", classJob.getSimpleName());
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(classJob);
		// factory.setName(classJob.getSimpleName());
		// factoryBean.setDurability(true);
		return factory;
	}

	public static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long repeat) {
		SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
		factory.setStartDelay(0L);
		factory.setJobDetail(jobDetail);
		factory.setRepeatInterval(repeat * 1000); // giay
		// factory.setBeanName(jobDetail.getClass().getSimpleName());
		factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		return factory;
	}

}
