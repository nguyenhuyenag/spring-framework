package com.schedule.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.util.ConfigReader;

@Component
public class PutHoaDon implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(PutHoaDon.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("Job start after {}s", ConfigReader.JOB_TIME_PUT_HOADON);
	}

}