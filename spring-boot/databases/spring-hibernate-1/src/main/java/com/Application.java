package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.ProgramingRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ProgramingRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.findAll().forEach(t -> System.out.println(t));
	}

}
