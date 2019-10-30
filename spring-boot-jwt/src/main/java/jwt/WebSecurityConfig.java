package jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jwt.security.JwtFilterConfigurer;
import jwt.security.JwtProvider;

@Order(1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtProvider jwtProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Disable CSRF
		http.csrf().disable();
		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Entry points
		http.authorizeRequests() //
			.antMatchers("/api/user/login").permitAll() //
			.antMatchers("/users/signup").permitAll() //
			.antMatchers("/h2-console/**/**").permitAll()
			// Disallow everything else
			.anyRequest().authenticated();
		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/login");
		// Apply JWT
		http.apply(new JwtFilterConfigurer(jwtProvider));
		// Optional, if you want to test the API from a browser
		// http.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow swagger to be accessed without authentication
		web.ignoring().antMatchers("/v2/api-docs") //
				.antMatchers("/swagger-resources/**") //
				.antMatchers("/swagger-ui.html") //
				.antMatchers("/configuration/**") //
				.antMatchers("/webjars/**") //
				.antMatchers("/public")
				// Un-secure H2 Database (for testing purposes, H2 console shouldn't be
				// unprotected in production)
				.and().ignoring().antMatchers("/h2-console/**/**");
	}

	// Setup Service find User in database & PasswordEncoder
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
