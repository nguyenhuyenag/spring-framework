package quartz.config;

import org.apache.commons.lang3.ArrayUtils;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig { // -> Cách 1

	private static final Logger LOG = LoggerFactory.getLogger(QuartzConfig.class);

	@Value("${spring.quartz.properties.org.quartz.scheduler.enabled:true}")
	private boolean enabled;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		return new SpringBeanJobFactory();
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger... triggers) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setAutoStartup(enabled);
		factory.setOverwriteExistingJobs(true);
		factory.setJobFactory(springBeanJobFactory());
		factory.setWaitForJobsToCompleteOnShutdown(true);
		if (ArrayUtils.isNotEmpty(triggers)) {
			factory.setTriggers(triggers);
			LOG.info("Set {} triggers", triggers.length);
		}
		return factory;
	}

//	public static JobDetailFactoryBean createJobDetail(Class<? extends Job> classJob) {
//		LOG.info("createJobDetail(jobClass={})", classJob.getSimpleName());
//		JobDetailFactoryBean factory = new JobDetailFactoryBean();
//		factory.setJobClass(classJob);
//		return factory;
//	}
//
//	public static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long repeatTime) {
//		SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
//		// factory.setStartDelay(0L);
//		factory.setJobDetail(jobDetail);
//		factory.setRepeatInterval(1000 * repeatTime); // giay
//		factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//		return factory;
//	}

}
