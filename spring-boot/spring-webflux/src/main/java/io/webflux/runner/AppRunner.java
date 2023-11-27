package io.webflux.runner;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import io.webflux.client.HttpClient;

@Component
public class AppRunner implements ApplicationRunner {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Document doc = HttpClient.getUser();
		mongoTemplate.insert(doc, "user");
		System.out.println("OK");
	}

}
