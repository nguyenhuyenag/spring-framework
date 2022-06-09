package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JWTAuthenticationFilter;
import com.filter.JWTLoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //
			.passwordEncoder(passwordEncoder()) //
			.withUser("huyennv") //
			.password(passwordEncoder().encode("123456")) //
			.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //
		http.headers().frameOptions().disable();  // Error: localhost refused to connect
		http.authorizeRequests() //
			.antMatchers("/auth/**", "/h2-console/**").permitAll() //
			.anyRequest().authenticated() //
			.and() //
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) //
			.addFilterBefore(new JWTLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class) //
			.exceptionHandling();
	}

}
