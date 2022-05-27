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
			throw new UsernameNotFoundException("User `" + username + "` was not found!");
			// throw new BadCredentialsException("User `" + username + "` was not found!");
		}
		User user = opt.get();
		System.out.println("Found User: " + user);
		List<GrantedAuthority> listGrants = new ArrayList<>();
		List<String> listRoles = repository.getListRolesByUserId(user.getUserId());
		if (listRoles != null) {
			for (String role : listRoles) {
				// ROLE_USER, ROLE_ADMIN, ...
				listGrants.add(new SimpleGrantedAuthority(role));
			}
		}
        UserDetails userDetails = org.springframework.security.core.userdetails
        		.User.withUsername(user.getUsername())
        			 .password(user.getPassword())
        			 .disabled(user.getEnabled() == 0) //
        			 .authorities(listGrants)
        			 .build();
        return userDetails;
	}

}
