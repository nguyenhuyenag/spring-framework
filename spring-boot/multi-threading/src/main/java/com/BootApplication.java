package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication { // implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args); // .close();
	}

	// @Autowired
	// private AsyncMethod asyncMethod;

//	@Override
//	public void run(String... args) throws Exception {
////		List<String> users = Arrays.asList("nguyenhuyenag", "huyennv");
////		users.forEach(t -> {
////			CompletableFuture<User> f = asyncMethod.findUser(t);
////			f.thenAccept(result -> {
////				System.out.println("future2 completed, result = " + result.getPassword());
////			});
////			User user = asyncMethod.findUserWithoutAsync(t);
////			if (user != null) {
////				System.out.println(user.getPassword());
////			}
////		});
//	}

}
