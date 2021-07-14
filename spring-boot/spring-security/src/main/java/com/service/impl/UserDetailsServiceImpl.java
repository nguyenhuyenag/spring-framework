package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = repository.findByUsername(username);
		if (!opt.isPresent()) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database!");
		}
		User user = opt.get();
		System.out.println("Found User: " + user);
		List<String> roleNames = repository.getListRolesByUserId(user.getUserId());
		List<GrantedAuthority> listGrant = new ArrayList<>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN, ...
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				listGrant.add(authority);
			}
		}
		return (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), listGrant);
	}

}
