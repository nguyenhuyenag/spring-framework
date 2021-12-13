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

	// @Autowired
	// private HttpServletRequest request;
	// private String getClientIP() {
	// String xfHeader = request.getHeader("X-Forwarded-For");
	// if (xfHeader == null) {
	// return request.getRemoteAddr();
	// }
	// return xfHeader.split(",")[0];
	// }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean disabled = false;
		String uname = null, password = null;
		final User user = userService.findByUsername(username);
		if (user == null) {
			LOG.info("[loadUserByUsername]: Account `" + username + "` was not found!");
		} else {
			uname = user.getUsername();
			password = user.getPassword();
			disabled = user.getDisabled() == 0 ? false : true;
		}
		return org.springframework.security.core.userdetails.User //
				.withUsername(uname) //
				.password(password) //
				.disabled(disabled) //
				.authorities(new HashSet<>()) //
				.build();
	}
}
