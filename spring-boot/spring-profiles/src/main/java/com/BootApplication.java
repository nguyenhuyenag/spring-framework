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

	public static void restart(String profile) {
		System.out.println("Profile param: " + profile);
		Thread thread = new Thread(() -> {
			if (context != null) {
				context.close();
			}
			context = SpringApplication.run(BootApplication.class, "--spring.profiles.active=" + profile);
		});
		thread.setDaemon(false);
		thread.start();
	}

}
