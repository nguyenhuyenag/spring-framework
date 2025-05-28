package dev.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.boot.exception.NotFoundException;
import dev.entity.User;
import dev.repository.UserRepository;

@Service
public class UserService {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	private String randomMail() {
		List<String> list = Arrays.asList("yahoo.com", "gmail.com", "yandex.com", "amazon.com");
		Random r = new Random();
		return list.get(r.nextInt(list.size()));
	}

	public int init() {
		String name, email;
		List<String> listEmails = repository.getAllEmails();
		List<User> list = new ArrayList<>();
		while (list.size() < 5) {
			name = RandomStringUtils.randomAlphabetic(5);
			email = name.toLowerCase() + "@" + randomMail();
			if (!listEmails.contains(email)) {
				list.add(new User(null, name, email, null));
			} else {
				System.out.println("Duplicate email!");
			}
		}
		repository.saveAll(list);
		return list.size();
	}

	public long count() {
		return repository.count();
	}

	public boolean existsById(int id) {
		return repository.existsById(id);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

	public User getById(Integer id) {
		Optional<User> opt = repository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new NotFoundException("User không tồn tại!");
	}

}
