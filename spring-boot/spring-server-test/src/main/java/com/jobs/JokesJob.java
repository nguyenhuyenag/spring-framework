package com.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JokesJob implements Job {
	
	private static final Logger LOG = LoggerFactory.getLogger(JokesJob.class);

	@Override
	public void execute(JobExecutionContext context) {
		LOG.info("Job1 run...");
	}

}
