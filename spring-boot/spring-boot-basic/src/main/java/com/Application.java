package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.boot.properties.Configuration;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(Application.class, args);
	}

	public static void restart() {
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(Application.class, "--spring.profiles.active=your_profile");
		});
		thread.setDaemon(false);
		thread.start();
	}
	
	@Autowired
	Configuration configuration;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(configuration.getLanguage());
	}

}
