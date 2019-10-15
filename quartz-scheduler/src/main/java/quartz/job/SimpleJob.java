package quartz.job;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import quartz.entity.Quartz;
import quartz.repository.QuartzRepository;

public class SimpleJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleJob.class);

	@Autowired
	QuartzRepository quartzRepository;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		String text = RandomStringUtils.randomAlphabetic(5);
		LOG.info(text);
		Quartz entity = new Quartz(null, text, new Date());
		quartzRepository.save(entity);
	}
}