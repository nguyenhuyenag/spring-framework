package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Spring2JWTApplication extends SpringBootServletInitializer implements CommandLineRunner {

	// WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Spring2JWTApplication.class);
	}

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Spring2JWTApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
