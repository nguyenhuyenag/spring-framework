package quartz.job;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import quartz.entity.Quartz;
import quartz.repository.QuartzRepository;

public class CreateJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(CreateJob.class);

	@Autowired
	QuartzRepository quartzRepository;

	@Autowired
	EntityManagerFactory emf;

	@Override
	public void execute(JobExecutionContext context) {

		try {

			List<Quartz> list = quartzRepository.findAll();

			if (list.size() > 10) {
				System.out.print(Colors.BOLD_RED);
				LOG.info("Size of list > 10 ...");
				System.out.print(Colors.RESET);
				quartzRepository.deleteAll();

				System.out.print(Colors.BOLD_RED);
				LOG.info("Delete all rows ...");
				System.out.print(Colors.RESET);
				quartzRepository.resetId();

				System.out.print(Colors.BOLD_RED);
				LOG.info("Reset id ...");
				System.out.print(Colors.RESET);
				TimeUnit.SECONDS.sleep(2);
			}

			System.out.print(Colors.BOLD_RED);
			LOG.info("Size now = " + quartzRepository.findAll().size());
			System.out.print(Colors.RESET);

			String text = RandomStringUtils.randomAlphabetic(5);
			Quartz entity = new Quartz(null, text, new Date());
			quartzRepository.save(entity);
			LOG.info("Add new ...");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
