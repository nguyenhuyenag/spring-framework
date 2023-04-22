package quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
// @DisallowConcurrentExecution
public class Job1 implements Job {

	private static final RestTemplate restTemplate = new RestTemplate();
	
	private final static String URL = "https://random-data-api.com/api/users/random_user";

	@Override
	public void execute(JobExecutionContext context) {
		getJson();
	}

	public void getJson() {
		try {
			String json = restTemplate.getForObject(URL, String.class);
			// String joke = json.getValue().getJoke();
			System.out.println("Job 1: " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
