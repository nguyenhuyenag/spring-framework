package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

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
			throw new BadCredentialsException("BLOCK_IP");
		}
		Optional<User> opt = repository.findByUsername(username);
		if (!opt.isPresent()) {
			LOG.info("User `{}` was not found!", username);
			throw new UsernameNotFoundException("User `" + username + "` was not found!");
		}
		User user = opt.get();
		LOG.info("Found user {}", user);
		if (user.isDisabled()) {
			LOG.info("User `{}` is disabled", username);
			// throw new BadCredentialsException("USER_DISABLED");
		}
		List<GrantedAuthority> listGrants = new ArrayList<>();
		// [ROLE_USER, ROLE_ADMIN, ...]
		List<String> roles = userService.getRolesByUserId(user.getUserId());
		LOG.info("Roles of `{}` is {}", user.getUsername(), roles);
		if (roles != null) {
			for (String role : roles) {
				listGrants.add(new SimpleGrantedAuthority(role));
			}
		}
		return org.springframework.security.core.userdetails.User //
				.withUsername(user.getUsername()) //
				.password(user.getPassword()) ///
				.disabled(user.isDisabled()) //
				.authorities(listGrants) //
				.build();
	}

}
