package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.auth.LoginFailureHandler;
import com.auth.MyAuthenticationSuccessHandler;
import com.util.Roles;

//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.auth.LoginFailureHandler;
//import com.auth.MyAuthenticationSuccessHandler;
//import com.util.Roles;
//
@Configuration
@EnableWebSecurity
public class SecurityConfiguration { // extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)	// Cài đặt dịch vụ để tìm kiếm User trong Database
//			.passwordEncoder(passwordEncoder());	// Cài đặt PasswordEncoder
//	}

	private final String[] AUTH_WHITELIST = { "/static/**", "/login", "/logout", "/favicon.ico" };
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers(AUTH_WHITELIST);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				.antMatchers("/admin").hasAuthority(Roles.ROLE_ADMIN.name())
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
		http
			.formLogin(login -> login
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/j_spring_security_check") // The URL to submit the username and password to
				.successHandler(successHandler())
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				.failureHandler(failureHandler()));
		return http.build();
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests(request -> request
//				.antMatchers(AUTH_WHITELIST).permitAll() // Public URL
//				.antMatchers("/admin").hasAuthority(Roles.ROLE_ADMIN.name())
//				.anyRequest().authenticated());
//			
//		http.authorizeRequests(withDefaults())
//			.formLogin(login -> login
//				.loginPage("/login")
//				.usernameParameter("username")
//				.passwordParameter("password")
//				.loginProcessingUrl("/j_spring_security_check") //The URL to submit the username and password to
//				.successHandler(successHandler())
//				.defaultSuccessUrl("/")
//				.failureUrl("/login?error=true")
//				.failureHandler(failureHandler()))
//			.logout(logout -> logout
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // csrf logout
//				// .logoutSuccessHandler(logoutSuccessHandler())
//				.logoutSuccessUrl("/login?logout")
//				.invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID"));
//				
//		http.authorizeRequests(withDefaults())
//			.exceptionHandling(handling -> handling.accessDeniedPage("/403"));
//				
//		http.rememberMe(remember -> {
//			int millis = (int) TimeUnit.DAYS.toSeconds(1);
//			// System.out.println("rememberMe: " + millis + "s");
//			remember.key("secretAndUnique")
//					.rememberMeParameter("rememberMe") // Name of checkbox at login page
//					.rememberMeCookieName("remember-me-name")
//					.tokenValiditySeconds(millis);
//		});
//
//		// http.sessionManagement().maximumSessions(1); // Limit login (thiết bị đăng nhập)
//	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new MyAuthenticationSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new LoginFailureHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
