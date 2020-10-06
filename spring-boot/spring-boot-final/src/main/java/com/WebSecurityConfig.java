package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
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

import com.exception.Http401Unauthorized;
import com.exception.Http403Forbidden;
import com.filter.JWTAuthenticationFilter;
import com.filter.JWTLoginFilter;

@Order(1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private RedisTemplate<String, String> redis;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder am) throws Exception {
		am.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable().authorizeRequests(); // Disable CSRF
		// http.exceptionHandling() //
		// .authenticationEntryPoint(new Http401Unauthorized()) // 401
		// .accessDeniedHandler(new Http403Forbidden()); // 403
		// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// // disable Spring session
		// http.authorizeRequests() //
		// .antMatchers("/").permitAll() //
		// .antMatchers("/favicon.ico").permitAll() //
		// // .antMatchers(HttpMethod.GET, PERMIT_ALL_GET).permitAll() //
		// // .antMatchers(HttpMethod.POST, PERMIT_ALL_POST).permitAll() //
		// .anyRequest().authenticated(); //
		// http.addFilterBefore(new JWTLoginFilter(authenticationManager()),
		// UsernamePasswordAuthenticationFilter.class) //
		// .addFilterBefore(new JWTAuthenticationFilter(authenticationManager()),
		// UsernamePasswordAuthenticationFilter.class) //
		// .headers() //
		// .cacheControl();
		http.csrf().disable() // Disable csrf
				// .antMatchers("/p/*").hasAnyRole("ADMIN", "USER") //
				// .antMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN") //
				.authorizeRequests() //
				.antMatchers("/favicon.ico").permitAll() //
				.antMatchers("/api/public/**").permitAll() //
				.antMatchers("/admin/**").hasRole("ADMIN") //
				.antMatchers(HttpMethod.POST, "/auth/logout/").permitAll() //
				.anyRequest().authenticated().and() //
				.addFilterBefore(new JWTLoginFilter(redis, authenticationManager()),
						UsernamePasswordAuthenticationFilter.class) //
				.addFilterBefore(new JWTAuthenticationFilter(redis, authenticationManager()),
						UsernamePasswordAuthenticationFilter.class) //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //
				.exceptionHandling() //
				.authenticationEntryPoint(new Http401Unauthorized()) //
				.accessDeniedHandler(new Http403Forbidden()).and() //
				.headers().cacheControl();
	}

}
