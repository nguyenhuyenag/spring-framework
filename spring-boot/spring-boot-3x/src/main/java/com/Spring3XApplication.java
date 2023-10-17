package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Spring3XApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring3XApplication.class, args);
	}

}
