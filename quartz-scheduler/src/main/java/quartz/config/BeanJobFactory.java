package quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public final class BeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(BeanJobFactory.class);

	private transient AutowireCapableBeanFactory factory;

	@Override
	public void setApplicationContext(final ApplicationContext context) {
		factory = context.getAutowireCapableBeanFactory();
	}

	@Override
	protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
		final Object job = super.createJobInstance(bundle);
		LOG.info("Create job instance...");
		factory.autowireBean(job);
		return job;
	}
}