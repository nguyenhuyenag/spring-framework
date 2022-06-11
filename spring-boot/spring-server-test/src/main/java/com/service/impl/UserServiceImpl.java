package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		try {
			Optional<User> opt = userRepository.findByUsername(username);
			if (opt.isPresent()) {
				return opt.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean save(User user) {
		return userRepository.save(user) != null;
	}

	@Override
	public List<User> loadAll() {
		return userRepository.getAllUser();
	}

}
