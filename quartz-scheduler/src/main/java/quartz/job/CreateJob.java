package quartz.job;

import javax.persistence.EntityManagerFactory;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import quartz.repository.QuartzRepository;

public class CreateJob implements Job {

	// private static final Logger LOG = LoggerFactory.getLogger(CreateJob.class);

	@Autowired
	QuartzRepository quartzRepository;

	@Autowired
	EntityManagerFactory emf;

	@Override
	public void execute(JobExecutionContext context) {
		long time = 9223372036854775807l;
		System.out.println(time);
	}
}
