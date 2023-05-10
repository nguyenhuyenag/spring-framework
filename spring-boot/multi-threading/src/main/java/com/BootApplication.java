package com;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.spring.AsyncMethod;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	// private static final Logger logger = LoggerFactory.getLogger(AsyncMethod.class);

	@Autowired
	private AsyncMethod asyncMethod;

	@Override
	public void run(String... args) throws Exception {
		String username = "nguyenhuyenag";
		CompletableFuture<User> f = asyncMethod.findUser(username);
		f.thenAccept(result -> {
			System.out.println("future2 completed, result = " + result.getPassword());
		});
		User user = asyncMethod.findUserWithoutAsync(username);
		if (user != null) {
			System.out.println(user.getPassword());
		}
	}

}
