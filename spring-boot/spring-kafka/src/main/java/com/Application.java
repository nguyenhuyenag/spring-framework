
package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.repository.LIpsumRepository;
import com.util.KafkaUtils;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	LIpsumRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// KafkaUtils.createTopic("topicName2022");
		// KafkaUtils.deleteTopics(Arrays.asList(""));
		KafkaUtils.showTopicsInfor();
		// System.out.println(loremIpsum.getWords( 150, 2 ));
		// repository.findAllByStatusLimit(20).forEach(t->System.out.println(t));
	}

}
