package com.config;

import static org.springframework.security.config.Customizer.withDefaults;

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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth.LoginFailureHandler;
import com.auth.MyAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)		// Cài đặt dịch vụ để tìm kiếm User trong Database
				.passwordEncoder(passwordEncoder()); 	// Cài đặt PasswordEncoder
	}

	private static final String[] WHITE_LIST = { "/static/**", "/login", "/logout", "/favicon.ico" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(request -> request
				.antMatchers(WHITE_LIST).permitAll() // Public URL
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
				// .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
				.anyRequest().authenticated());
			
		http.authorizeRequests(withDefaults())
			.formLogin(login -> login
					.loginPage("/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.loginProcessingUrl("/j_spring_security_check") // the URL to submit the username and password to
					.successHandler(successHandler())
					.failureUrl("/login?error=true")
					.failureHandler(loginFailureHandler))
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // csrf logout
					// .logoutSuccessHandler(logoutSuccessHandler())
					.logoutSuccessUrl("/login?logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID"));
				
			http.authorizeRequests(withDefaults())
				.exceptionHandling(handling -> handling.accessDeniedPage("/403"));
				
			http.rememberMe(me -> me
					.key("mySecretKey")
					.rememberMeParameter("rememberMe") 			// Name of checkbox at login page
					.rememberMeCookieName("remember-me-name")
					.tokenValiditySeconds(1 * 24 * 60 * 60)); 	// 1 day (default is 14 days)

		// http.sessionManagement().maximumSessions(1); // Limit login (thiết bị đăng nhập)
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
//		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
//		handler.setUseReferer(true);
//		handler.setDefaultTargetUrl("/");
//		handler.setAlwaysUseDefaultTargetUrl(true);
//		return handler;
		return new MyAuthenticationSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
