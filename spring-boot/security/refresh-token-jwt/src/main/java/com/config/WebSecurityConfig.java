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

import com.exception.Http401;
import com.exception.Http403;
import com.filter.AuthenticationRequestFilter;
import com.filter.JWTLoginFilter;
import com.service.RefreshTokenService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	prePostEnabled = false,
	securedEnabled = false,		// for @RolesAllowed
	jsr250Enabled = true		// for @RolesAllowed
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService) //
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session cookie for API endpoints
		.and()
		.authorizeRequests() // no web forms for the REST API so no CSRF tokens will be created or checked
			.antMatchers("/auth/**")
			.permitAll()
		.anyRequest()
			.authenticated()
		.and()
			.addFilterBefore(new AuthenticationRequestFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTLoginFilter(authenticationManager(), refreshTokenService), UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling()
			.authenticationEntryPoint(new Http401())
			.accessDeniedHandler(new Http403());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
