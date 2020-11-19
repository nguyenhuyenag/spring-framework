package quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import quartz.service.QuartzService;

// @Component
// @Qualifier("Job1")
public class CreateJob implements Job {

	@Autowired
	private QuartzService service;

	@Override
	public void execute(JobExecutionContext context) {
		service.insert();
		service.delete();
	}
	
}
