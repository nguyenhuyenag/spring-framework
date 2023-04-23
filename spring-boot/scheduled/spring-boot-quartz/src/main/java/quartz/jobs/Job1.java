package quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
// @DisallowConcurrentExecution
public class Job1 implements Job {

	public RestTemplate restTemplate = new RestTemplate();

	public String URL = "https://random-data-api.com/api/users/random_user";

	@Override
	public void execute(JobExecutionContext context) {
		try {
			// String json = restTemplate.getForObject(URL, String.class);
			System.out.println(this.getClass().getSimpleName() + ": " + TimeUtils.now());
			// System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
