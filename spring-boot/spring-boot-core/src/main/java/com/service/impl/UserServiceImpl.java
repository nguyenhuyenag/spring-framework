package com.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	// private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public void init() {
		String name, email;
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			name = RandomStringUtils.randomAlphabetic(5);
			email = name.toLowerCase() + "@mail.com";
			list.add(new User(null, name, email, null));
		}
		userRepository.saveAll(list);
		// LOG.info("Save all complete");
		System.out.println("Save all complete");
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
