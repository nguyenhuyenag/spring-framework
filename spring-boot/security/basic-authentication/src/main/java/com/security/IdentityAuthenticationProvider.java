package com.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class IdentityAuthenticationProvider implements AuthenticationProvider {

	UserDetails isValidUser(String username, String password) {
		if ("user".equalsIgnoreCase(username) && "password".equals(password)) {
			return User.withUsername(username) //
					.password(password)//
					.roles("USER_ROLE") //
					.build();
		}
		return null;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = isValidUser(username, password);
		if (userDetails == null) {
			throw new BadCredentialsException("Incorrect user credentials !!");
		}
		return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}