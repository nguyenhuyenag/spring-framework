package com;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.service.JokesService;

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
	JokesService jokesService;

	@Override
	public void run(String... args) throws Exception {
		try {
			URL url = new URL("https://www.example222.com/");
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();

			int responseCode = huc.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.out.println("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
