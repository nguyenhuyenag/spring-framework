package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Securing the urls and allowing role-based access to these urls.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //
				.httpBasic().and().authorizeRequests() //
				.antMatchers("/user").hasRole("USER") //
				.antMatchers("/admin").hasRole("ADMIN");
	}

	// In-memory authentication to authenticate the user i.e. the user credentials
	// are stored in the memory
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()) //
				.withUser("user") //
				.password("$2a$10$U53MVE4aeeLoCyZ4fV4M/.ghStL.wRo.FRXf1bkSCIq3CiVvgxRAi") //
				.roles("USER") //
			.and() //
				.withUser("admin") //
				// .password("{noop}password") //
				.password("$2a$10$U53MVE4aeeLoCyZ4fV4M/.ghStL.wRo.FRXf1bkSCIq3CiVvgxRAi") //
				.roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
