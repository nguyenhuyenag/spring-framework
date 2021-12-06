package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.util.MD5PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	// @Autowired
    // private LoginFailureHandler loginFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new MD5PasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService) // cài đặt dịch vụ để tìm kiếm User trong Database
			.passwordEncoder(passwordEncoder()); 	// cài đặt PasswordEncoder
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// Các trang không yêu cầu login
		http.authorizeRequests()
			.antMatchers("/", "/login", "/logout", "/static/**").permitAll()
		 	.anyRequest().authenticated();
		
		// Cấu hình cho login
		http.authorizeRequests().and().formLogin() //
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login") //
				.usernameParameter("username") //
				.passwordParameter("password")
				.defaultSuccessUrl("/tracuu") //
				.failureUrl("/login?error=true") //
				.failureHandler(new LoginFailureHandler()) // 
				// cấu hình logout
				.and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");
	}

}
