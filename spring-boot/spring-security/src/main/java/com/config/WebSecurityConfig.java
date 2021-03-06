package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService) // cài đặt dịch vụ để tìm kiếm User trong Database
			.passwordEncoder(passwordEncoder()); 	// cài đặt PasswordEncoder
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		// các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		
		// yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN
		http.authorizeRequests().antMatchers("/user-info").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		// trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		
		// Cấu hình cho Login Form.
		http.authorizeRequests()
			.and()
				.formLogin() //
				.loginPage("/login") //
				.loginProcessingUrl("/j_spring_security_check") //  the URL to submit the username and password to
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/") // the landing page after an unsuccessful login
				.failureUrl("/login?error=true")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout-successful");
		
		// AccessDeniedException
		http.authorizeRequests()
			.and()
				.exceptionHandling()
				.accessDeniedPage("/403");
		
	}

}
