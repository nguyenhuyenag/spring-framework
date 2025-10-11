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
	
	@Autowired
    private LoginFailureHandler loginFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests() //
			.antMatchers("/", "/login", "/logout", "/static/**", "/public/**").permitAll()
		 	.anyRequest().authenticated();
		http.authorizeRequests().and()
			.formLogin() //
				.loginPage("/login") //
				.loginProcessingUrl("/j_spring_security_check") //
				.usernameParameter("email") //
				.passwordParameter("password") //
				.defaultSuccessUrl("/quanlytaikhoan") //
				.failureUrl("/login?error=true") //
				.failureHandler(loginFailureHandler)
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");
	}

}
