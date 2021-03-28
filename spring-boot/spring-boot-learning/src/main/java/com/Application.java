package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boot.properties.AutoConfig;

@SpringBootApplication
//@EnableAutoConfiguration
public class Application implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// PageableJPA.init();
	// PageableJPA.info();
	// PageableJPA.sortPage();
	// PageableJPA.showAllPage();
	// userRepository.getAllEmails().forEach(t->System.out.println(t));
	// System.out.println(userRepository.getAllEmails());
	
	//@Value("${config.url}")
	//private String url;
	
	@Override
	public void run(String... args) throws Exception {
		// System.out.println(ReadProperties.URL);
		AutoConfig a = new AutoConfig();
		System.out.println(a.getValue());
	}

}
