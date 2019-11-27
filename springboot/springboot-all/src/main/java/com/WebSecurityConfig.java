package com;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.Http401Unauthorized;
import com.filter.JWTAuthenticationFilter;
import com.filter.JWTLoginFilter;

@Order(1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	private final String[] PERMIT_ALL_GET = { "/api/user/load-all" };
	private final String[] PERMIT_ALL_POST = { "/api/user/register" };

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder am) throws Exception {
		am.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // Disable CSRF
		http.exceptionHandling() //
			.authenticationEntryPoint(new Http401Unauthorized()); // handles bad credentials
			// http.accessDeniedHandler(accessDeniedHandler);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // disable Spring session
		http.authorizeRequests() //
			.antMatchers("/").permitAll() //
			.antMatchers("/favicon.ico").permitAll() //
			.antMatchers(HttpMethod.GET, PERMIT_ALL_GET).permitAll() //
			.antMatchers(HttpMethod.POST, PERMIT_ALL_POST).permitAll() //
			.anyRequest().authenticated(); //
		http.addFilterBefore(new JWTLoginFilter("/auth/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) //
			.addFilterBefore(new JWTAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class) //
			.headers().cacheControl();
	}

}
