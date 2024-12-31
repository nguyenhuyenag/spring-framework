package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MultiThreadingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiThreadingTestApplication.class, args);
	}

}
