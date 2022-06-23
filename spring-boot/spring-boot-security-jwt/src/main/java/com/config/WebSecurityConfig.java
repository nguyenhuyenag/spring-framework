package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JWTAuthenticationFilter;
import com.filter.JWTLoginFilter;
import com.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	// @Autowired
	// private Http401 unauthorizedHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService) //
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session cookie for API endpoints
			.and() //
			.authorizeRequests() // no web forms for the REST API so no CSRF tokens will be created or checked
			.antMatchers("/auth/**").permitAll() //
			.anyRequest().authenticated() //
			.and() //
			.addFilterBefore(new JWTAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class) //
			.addFilterBefore(new JWTLoginFilter(authenticationManager(), userService), UsernamePasswordAuthenticationFilter.class) //
			.exceptionHandling();
			// .authenticationEntryPoint(unauthorizedHandler)
			// .accessDeniedHandler(new Http403());
			// .headers().cacheControl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
