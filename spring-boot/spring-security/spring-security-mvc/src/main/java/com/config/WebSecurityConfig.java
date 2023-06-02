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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		auth.userDetailsService(userDetailsService) // cài đặt dịch vụ để tìm kiếm User trong Database
				.passwordEncoder(passwordEncoder()); // cài đặt PasswordEncoder
	}

	// @Bean
	// public LogoutSuccessHandler logoutSuccessHandler() {
	// return new CustomLogoutSuccessHandler();
	// }
	
	private static final String[] WHITE_LIST = {
		"/static/**",
		"/login", "/logout"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(request -> {
			// các trang không yêu cầu login
			request.antMatchers(WHITE_LIST).permitAll()
					// trang chỉ dành cho ADMIN
					.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
					// yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN
					// .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
					.antMatchers("/**").authenticated();
		});

		// Cấu hình cho Login Form
		http.authorizeRequests().and() //
			.formLogin() //
				.loginPage("/login") //
				.usernameParameter("username") //
				.passwordParameter("password") //
				.loginProcessingUrl("/j_spring_security_check") // the URL to submit the username and password to
				// .successHandler(successHandler())
				// .defaultSuccessUrl("/") // the landing page after an unsuccessful login
				.failureUrl("/login?error=true") //
				.failureHandler(loginFailureHandler) //
			.and() //
			.logout() //
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // csrf logout
				// .logoutSuccessHandler(logoutSuccessHandler());
				.logoutSuccessUrl("/login?logout") //
				.invalidateHttpSession(true) //
				.deleteCookies("JSESSIONID");

		// AccessDeniedException
		http.authorizeRequests().and() //
			.exceptionHandling() //
			.accessDeniedPage("/403");

		// Remember me
		http.rememberMe() //
			.key("mySecretKey") //
			.rememberMeParameter("rememberMe") // name of checkbox at login page
			.rememberMeCookieName("remember-me-name") //
			.tokenValiditySeconds(1 * 24 * 60 * 60); // 1 days (default is 14 days)

		// http.sessionManagement().maximumSessions(1); // limit login
	}
	
//	@Bean
//	public AuthenticationSuccessHandler successHandler() {
//	    return new CustomAuthenticationSuccessHandler();
//	}

}
