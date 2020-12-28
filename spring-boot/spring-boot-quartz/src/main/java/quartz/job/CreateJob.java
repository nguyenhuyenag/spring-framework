package quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import quartz.dao.Jokes;
import quartz.service.QuartzService;

// @Component
// @Qualifier("Job1")
public class CreateJob implements Job {

	@Autowired
	QuartzService service;

	private final static String URL = "http://api.icndb.com/jokes/random";

	public static void getJson() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Jokes json = restTemplate.getForObject(URL, Jokes.class);
			String joke = json.getValue().getJoke();
			System.out.println(joke.replaceAll("&quot;", "\"") + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(JobExecutionContext context) {
		// service.insert();
		// service.delete();
		getJson();
	}

}
