package com;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.util.TimeUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	// WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		// Assert.notNull(null, "password cannot be null");
		System.out.println("TIME: " + TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		// System.out.println(encoder.encode("123456"));
		
		Response res = RestAssured.given()
			      .header(HttpHeaders.AUTHORIZATION, "Bearer " + "")
			      .get("http://localhost:8081/resource-server-jwt/foos");
	}

}
