package com.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> opt = repository.findByUsername(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("User `" + username + "` was not found!");
		}
		User user = opt.get();
		return org.springframework.security.core.userdetails.User //
				.withUsername(user.getUsername()) //
				.password(user.getPassword()) //
				.disabled(false) //
				.authorities(getAuthority(user)) //
				.build();
	}
}
