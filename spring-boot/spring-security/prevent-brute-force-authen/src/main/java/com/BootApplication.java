package com;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.User;
import com.repository.UserRepository;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// PasswordEncoder p = new BCryptPasswordEncoder();
		// System.out.println(p.encode("123456"));
		// System.out.println(TimeUnit.HOURS.toMillis(1));
		Optional<User> findById = userRepository.findById(1);
		findById.ifPresent(t -> {
			System.out.println(t);
		});
	}

}
