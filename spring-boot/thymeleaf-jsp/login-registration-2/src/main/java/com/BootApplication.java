package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.repository.UserRepository;

@SpringBootApplication
public class BootApplication extends SpringBootServletInitializer implements CommandLineRunner {

	/* JAR */
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootApplication.class);
	}
	
	@Autowired
	UserRepository repository;
	

	@Override
	public void run(String... args) throws Exception {
		// repository.increaseFailedAttempt("huyennv");
	}

}
