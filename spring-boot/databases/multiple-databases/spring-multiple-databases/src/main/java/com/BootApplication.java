package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.StringUtils;

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

	@Override
	public void run(String... args) throws Exception {

		String s = "D:\\Java\\workspace\\maven\\java-tutorial";
		System.out.println(StringUtils.cleanPath(s));

	}

}
