package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.repository.UserRepository;

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
	UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		// Assert.notNull(null, "password cannot be null");
		// System.out.println("TIME: " + TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		Optional<User> findByUsername = repository.findByUsername("huyennv");
//		if (findByUsername.isPresent()) {
//			findByUsername.get().getRoles().forEach(t->System.out.println(t.getName()));
//		}
	}

}
