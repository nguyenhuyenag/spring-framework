package com.service.impl;

import java.util.HashSet;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			LOG.info("Username is empty!");
			throw new UsernameNotFoundException("Username is empty!");
		}
		final Optional<User> opt = repository.findByUsername(username);
		if (opt.isPresent()) {
			User user = opt.get();
			return org.springframework.security.core.userdetails.User //
					.withUsername(user.getUsername()) //
					.password(user.getPassword()) //
					.disabled(user.getStatus() == 0 ? true : false) //
					.authorities(new HashSet<>()) //
					.build();
		} else {
			LOG.info("[loadUserByUsername]: Account `" + username + "` was not found!");
			throw new UsernameNotFoundException("Account `" + username + "` was not found!");
		}
	}

}
