package com.service;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

//	public User findByUsername(String username) {
//		Optional<User> opt = userRepository.findByUsername(username);
//		if (opt.isPresent()) {
//			return opt.get();
//		}
//		return
//	}

	// @Override
	public boolean save(User user) {
		return userRepository.save(user) != null;
	}

	// @Override
	public List<User> loadAll() {
		return userRepository.getAllUser();
	}

	public String findAuthoritiesByUsername(String username) {
		StringJoiner joiner = new StringJoiner(",");
		Optional<User> opt = userRepository.findByUsername(username);
		if (opt.isPresent()) {
			opt.get().getRoles().forEach(t -> joiner.add("ROLE_" + t.getName()));
		}
		return joiner.toString();
	}

}
