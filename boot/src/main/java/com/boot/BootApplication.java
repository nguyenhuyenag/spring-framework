package com.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.boot.service.UserService;
import com.boot.util.ApplicationProperties;
import com.boot.util.Log4jProperties;

@SpringBootApplication
public class BootApplication extends SpringBootServletInitializer implements CommandLineRunner {

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	// WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootApplication.class);
	}

	@Autowired
	ApplicationProperties properties;

	@Autowired
	Log4jProperties log4jProperties;

	@Autowired
	UserService service;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(properties.getServerPort());
	}

}
