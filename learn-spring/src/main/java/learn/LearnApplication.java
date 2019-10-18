package learn;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import learn.entity.User;
import learn.repository.UserRepository;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		// List<User> list = new ArrayList<>();
		// for (int i = 0; i < 24; i++) {
		// String firstname = RandomStringUtils.randomAlphabetic(5);
		// String lastname = RandomStringUtils.randomAlphabetic(5);
		// String emailAddress = firstname.toLowerCase() + "@java.com";
		// list.add(new User(null, firstname, lastname, emailAddress));
		// }
		// userRepository.saveAll(list);

		// User user = userRepository.findUserByLastname("haMDs");
		// System.out.println(user);

		List<User> list = userRepository.startWith("v");
		list.forEach(System.out::println);
		// list.size();

	}

}
