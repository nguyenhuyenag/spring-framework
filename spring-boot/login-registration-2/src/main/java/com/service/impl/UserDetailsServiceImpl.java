package com.service.impl;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userService.findByUsername(username);
		if (user == null) {
			LOG.info("[loadUserByUsername]: Account `" + username + "` was not found!");
			throw new UsernameNotFoundException("[UserDetailsServiceImpl: Account `" + username + "` was not found!");
		}
		return org.springframework.security.core.userdetails.User //
				.withUsername(user.getUsername()) //
				.password(user.getPassword()) //
				.disabled(user.getDisabled() == 0 ? false : true) //
				.authorities(new HashSet<>()) //
				.build();
	}
}
