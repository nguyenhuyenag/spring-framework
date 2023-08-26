package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BasicAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
	    	.authorizeRequests(request -> {
	    		request.antMatchers("/user").hasRole("USER")
	               	   .antMatchers("/admin").hasRole("ADMIN")
	               	   .anyRequest()
	               	   .authenticated();
		    }).httpBasic(httpBasic -> {
		        httpBasic.authenticationEntryPoint(authenticationEntryPoint);
		    }).sessionManagement(session -> {
		        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		    });
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("user")
				.password("$2a$10$FmFXnXCQcqZyzMQ0JNtXN.Btw3/ItGPZkptxx6yurfiAPMZ2z98WO")
				.roles("USER")
			.and()
			.withUser("admin")
				// .password("{noop}password")
				.password("$2a$10$FmFXnXCQcqZyzMQ0JNtXN.Btw3/ItGPZkptxx6yurfiAPMZ2z98WO")
				.roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
