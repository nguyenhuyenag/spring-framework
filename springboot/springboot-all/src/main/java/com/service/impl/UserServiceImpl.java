package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.request.RegisterRequest;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> loadAll() {
		return userRepository.getAllUser();
	}

	@Override
	public void register(RegisterRequest dto) {
		// String email = dto.getEmail();
		// String username = dto.getUsername();
		// boolean isExists = userRepository.existsByUsernameOrEmail(username, email);
		// if (isExists) {
		// LOGGER.info("Username: " + username + " or email: " + email + " already
		// exists!");
		// return;
		// }
		// User entity = new User();
		// entity.setRole("");
		// entity.setEmail(dto.getEmail());
		// entity.setFullName(dto.getFullName());
		// entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		// entity.setUsername(dto.getUsername());
		// System.out.println(userRepository.save(entity) == null);
	}

}
