package learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import learn.repository.UserRepository;
import learn.service.UserService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		// userService.init();
		// String lastName = "HPPPl";
		// long count = userService.count();
		// System.out.println(count);
		// userRepository.findDistinctUserByFirstname().forEach(System.out::println);
		// User u = userRepository.findFirstByOrderByLastnameAsc();
		// System.out.println(u);

	}

}
