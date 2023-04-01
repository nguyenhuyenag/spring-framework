package com.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = repository.findByUsername(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("User '" + username + "' was not found!");
		}
		User user = opt.get();
		return org.springframework.security.core.userdetails.User //
				.withUsername(user.getUsername()) //
				.password(user.getPassword()) //
				.disabled(user.isEnabled()) //
				.authorities(user.getAuthorities()) //
				.build();
	}
}
