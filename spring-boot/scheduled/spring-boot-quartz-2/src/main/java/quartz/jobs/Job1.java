package quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import quartz.dao.Jokes;

@Component
// @DisallowConcurrentExecution
public class Job1 implements Job {

	private final static String URL = "http://api.icndb.com/jokes/random";

	private static final RestTemplate restTemplate = new RestTemplate();

	@Override
	public void execute(JobExecutionContext context) {
		getJson();
	}

	public static void getJson() {
		try {
			Jokes json = restTemplate.getForObject(URL, Jokes.class);
			String joke = json.getValue().getJoke();
			System.out.println("Job 1: " + joke.replaceAll("&quot;", "\""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
