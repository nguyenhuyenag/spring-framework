package quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Job2 implements Job {

	private static final RestTemplate restTemplate = new RestTemplate();

	private final static String URL = "https://randomuser.me/api/";

	@Override
	public void execute(JobExecutionContext context) {
		getJson();
	}

	public static void getJson() {
		try {
			String json = restTemplate.getForObject(URL, String.class);
			System.out.println("Job 2: " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
