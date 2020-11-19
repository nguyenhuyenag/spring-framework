package quartz.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import quartz.job.CreateJob;

@Configuration
public class SchedulerConfig {

	private static final Logger LOG = LoggerFactory.getLogger(SchedulerConfig.class);

	@Value("${repeat.interval}")
	private long interval; // millisecond

	@Bean
	public JobFactory jobFactory(ApplicationContext context) {
		BeanJobFactory factory = new BeanJobFactory();
		factory.setApplicationContext(context);
		return factory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("quartz.properties"));
		properties.afterPropertiesSet();
		return properties.getObject();
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory job, Trigger trigger) throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(job);
		factory.setQuartzProperties(quartzProperties());
		factory.setTriggers(trigger);
		LOG.info("Starting jobs...");
		return factory;
	}

	@Bean
	public JobDetailFactoryBean simpleJobDetail() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(CreateJob.class); // class job
		factory.setDurability(true);
		return factory;
	}

	@Bean
	// @Primary
	// @Qualifier("Job1")
	public SimpleTriggerFactoryBean simpleJobTrigger(JobDetail job) {
		LOG.info("simpleJobTrigger");
		SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
		factory.setJobDetail(job);
		factory.setStartDelay(0L);
		factory.setRepeatInterval(interval); // milliseconds
		factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		return factory;
	}
	
}
