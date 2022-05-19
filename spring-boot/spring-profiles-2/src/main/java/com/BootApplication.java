package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public String dev() {
		System.out.println("++++++++++++++ dev environment");
		return "dev";
	}

	@Bean
	@Profile("prod")
	public String prod() {
		System.out.println("++++++++++++++ prod environment");
		return "prod";
	}

}