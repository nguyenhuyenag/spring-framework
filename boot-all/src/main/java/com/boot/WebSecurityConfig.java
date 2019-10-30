package com.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.boot.filter.CustomAuthenticationEntryPoint;
import com.boot.filter.JWTAuthenticationFilter;
import com.boot.filter.JWTLoginFilter;

@Order(1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	private final String[] PERMIT_ALL_GET = {
		// "/api/user/load-all"
	};

	private final String[] PERMIT_ALL_POST = {
		"/api/user/register"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Disable CSRF
		http.csrf().disable();
		
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint()); // handles bad credentials
			// http.accessDeniedHandler(accessDeniedHandler);
		
		// disable session creation on Spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //
		
		http.authorizeRequests() //
				.antMatchers("/").permitAll() //
				.antMatchers("/favicon.ico").permitAll() //
				.antMatchers(HttpMethod.GET, PERMIT_ALL_GET).permitAll() //
				.antMatchers(HttpMethod.POST, PERMIT_ALL_POST).permitAll() //
				.anyRequest() //
				.authenticated(); //
		
		http.addFilterBefore(new JWTLoginFilter("/api/user/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) //
			.addFilterBefore(new JWTAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class) //
			.headers().cacheControl();
	}

	// Setup service find User in database & PasswordEncoder
	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	// }

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	// Setup BCryptPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
