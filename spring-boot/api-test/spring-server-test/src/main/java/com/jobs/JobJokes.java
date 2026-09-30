package com.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.JokesService;

@Component
public class JobJokes implements Job {
	
	// private static final Logger LOG = LoggerFactory.getLogger(JokesJob.class);
	
	@Autowired
	private JokesService jokesService;

	@Override
	public void execute(JobExecutionContext context) {
		jokesService.autoInsert();
	}

}
