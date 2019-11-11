package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		final User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		return org.springframework.security.core.userdetails.User //
				.withUsername(username) //
				.password(user.getPassword()) //
				.authorities(user.getListRoles()) //
				.accountExpired(false) //
				.accountLocked(false) //
				.credentialsExpired(false) //
				.disabled(false) //
				.build();
	}

}
