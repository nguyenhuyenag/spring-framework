package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.util.LoginAttemptService;
import com.util.RequestUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String ip = RequestUtils.getClientIPAddress(request);
		if (loginAttemptService.isBlocked(ip)) {
			// throw new RuntimeException("block_ip");
			throw new BadCredentialsException("block_ip");
		}
		Optional<User> opt = repository.findByUsername(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("User `" + username + "` was not found!");
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
		return org.springframework.security.core.userdetails.User //
				.withUsername(user.getUsername()) //
				.password(user.getPassword()) ///
				.disabled(user.getEnabled() == 0) //
				.authorities(listGrants) //
				.build();
	}

}
