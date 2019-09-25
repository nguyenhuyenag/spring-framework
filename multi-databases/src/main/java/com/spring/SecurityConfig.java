package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.filter.JWTAuthenticationEntryPoint;
import com.spring.filter.JWTAuthenticationFilter;
import com.spring.filter.JWTLoginFilter;

@Order(1)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf() //
				.disable() //
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //
				.authorizeRequests() //
				.antMatchers("/favicon.ico").permitAll() //
				.antMatchers(HttpMethod.POST, "/api/accounts/register").permitAll() //
				.antMatchers(HttpMethod.GET, "/api/file/read-text").permitAll() //
				.anyRequest().authenticated().and() //
				.addFilterBefore(new JWTLoginFilter("/api/auth/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class) //
				.addFilterBefore(new JWTAuthenticationFilter(userDetailsService),
						UsernamePasswordAuthenticationFilter.class) //
				.headers().cacheControl();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
