package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(BootApplication.class, args);
	}

	public static void restart() {
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(BootApplication.class, "--spring.profiles.active=your_profile");
		});
		thread.setDaemon(false);
		thread.start();
	}

}
