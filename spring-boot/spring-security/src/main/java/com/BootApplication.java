package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.UserRepository;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Autowired
	UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		
		//Optional<User> opt = repository.findById(1);
		//if (opt.isPresent()) {
			// opt.get().getUserRoles().forEach(t -> System.out.println(t.getRole().getRoleName()));
		//}
		
		// repository.getListRolesByUserId(1).forEach(t->System.out.println(t));
		
		//Optional<User> opt = repository.findByUsername("user1");
		//if (opt.isPresent()) {
		//	System.out.println(opt.get().toString());
		//}
	}

}
