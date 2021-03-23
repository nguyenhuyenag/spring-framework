package com;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.Trans;
import com.repository.TransRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	TransRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// repository.findAll().forEach(t -> System.out.println(t));
		Optional<Trans> opt = repository.findById(5);
		if (opt.isPresent()) {
			Trans entity = opt.get();
			System.out.println(entity);
			entity.setNo("0006");
			entity.setName(RandomStringUtils.randomAlphabetic(5).toUpperCase());
			repository.save(entity);
		}
		System.out.println(repository.findById(5).get());
	}

}
