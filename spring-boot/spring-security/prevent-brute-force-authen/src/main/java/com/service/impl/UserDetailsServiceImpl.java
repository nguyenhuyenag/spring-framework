package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.service.LoginAttemptService;
import com.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository repository;

//	@Autowired
//	private HttpServletRequest request;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		String ip = RequestUtils.getClientIPAddress(request);
//		if (loginAttemptService.isBlocked(ip)) {
//			throw new BadCredentialsException("BLOCK_IP");
//		}
		
		Optional<User> opt = repository.findByUsername(username);
		if (opt.isEmpty()) {
			LOG.info("User `{}` was not found!", username);
			throw new UsernameNotFoundException("User `" + username + "` was not found!");
		}
		
		User user = opt.get();
		
		// [ROLE_USER, ROLE_ADMIN, ...]
		List<GrantedAuthority> roles = userService.getGrantedAuthorityByUserId(user.getUserId());
		LOG.info("Roles of `{}`: {}", user.getUsername(), roles);
		return org.springframework.security.core.userdetails.User //
				.withUsername(user.getUsername()) //
				.password(user.getPassword())     //
				.accountLocked(user.isLocked())   //
				.disabled(user.isDisabled())      //
				// .accountExpired(false)
				// .credentialsExpired(false)
				.authorities(roles) //
				.build();
	}

}
