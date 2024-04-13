package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean save(User user) {
		return userRepository.save(user) != null;
	}

	public List<User> loadAll() {
		return userRepository.getAllUser();
	}

//	public String findAuthoritiesByUsername(String username) {
//		StringJoiner joiner = new StringJoiner(",");
//		Optional<User> opt = userRepository.findByUsername(username);
//		if (opt.isPresent()) {
//			opt.get().getRoles().forEach(t -> joiner.add("ROLE_" + t.getName()));
//		}
//		return joiner.toString();
//	}

}
