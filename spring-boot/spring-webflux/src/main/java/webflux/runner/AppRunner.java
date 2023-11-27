package webflux.runner;

import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import webflux.util.HttpClient;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		while (true) {
			Document doc = HttpClient.getUser();
			mongoTemplate.insert(doc, "user");
			System.out.println("OK");
			TimeUnit.SECONDS.sleep(5);
		}
	}

}
