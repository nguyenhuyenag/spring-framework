package com;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.publisher.PublisherEvent;

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

	@Override
	public void run(String... args) throws Exception {
		doSend();
	}

	@Autowired
	PublisherEvent customSpringEventPublisher;

	public void doSend() throws InterruptedException {
		int i = 0;
		while (true) {
			customSpringEventPublisher.publishCustomEvent(i + "");
			i++;
			TimeUnit.SECONDS.sleep(5);
		}
	}

}
