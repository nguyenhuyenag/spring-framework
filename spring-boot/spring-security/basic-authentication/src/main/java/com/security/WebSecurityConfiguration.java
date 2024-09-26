package com.security;

import lombok.RequiredArgsConstructor;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// @Autowired
	private final BasicAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
	    	.authorizeRequests(request -> {
	    		request.antMatchers("/user").hasRole("USER")
	               	   .antMatchers("/admin").hasRole("ADMIN")
	               	   .anyRequest().authenticated();
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
				.password(passwordEncoder().encode("123456"))
				.roles("USER")
			.and()
			.withUser("admin")
				// .password("{noop}password")
				.password(passwordEncoder().encode("123456"))
				.roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
