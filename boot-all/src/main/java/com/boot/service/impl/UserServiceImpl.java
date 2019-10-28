package com.boot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.entity.User;
import com.boot.repository.UserRepository;
import com.boot.request.RegisterRequest;
import com.boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> loadAll() {
		return userRepository.getAllUser();
	}

	@Override
	public void register(RegisterRequest dto) {
		String email = dto.getEmail();
		String username = dto.getUsername();
		boolean isExists = userRepository.existsByUsernameOrEmail(username, email);
		if (isExists) {
			LOGGER.info("Username: " + username + " or email: " + email + " already exists!");
			return;
		}
		User entity = new User();
		entity.setRole("");
		entity.setEmail(dto.getEmail());
		entity.setFullName(dto.getFullName());
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity.setUsername(dto.getUsername());
		System.out.println(userRepository.save(entity) == null);
	}

}
