package com.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	private String randomMail() {
		List<String> list = Arrays.asList("yahoo.com", "gmail.com", "yandex.com", "amazon.com");
		Random r = new Random();
		return list.get(r.nextInt(list.size()));
	}

	@Override
	public int init() {
		String name, email;
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			name = RandomStringUtils.randomAlphabetic(5);
			email = name.toLowerCase() + "@" + randomMail();
			list.add(new User(null, name, email, null));
		}
		userRepository.saveAll(list);
		return list.size();
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public boolean existsById(long id) {
		return userRepository.existsById(id);
	}

	@Override
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

//	@Override
//	public long countByLastname(String lastname) {
//		return userRepository.countByLastname(lastname);
//	}

}
