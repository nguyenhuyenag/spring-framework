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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth.CustomLoginFailureHandler;
import com.auth.CustomLoginSuccessHandler;
import com.auth.CustomLogoutHandler;
import com.util.Roles;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * Class được đánh dấu @Component nên chỉ cần @Autowired, không cần khai báo @Bean 
	 */
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;
	
	private final String[] AUTH_WHITELIST = { "/static/**", "/login", "/logout", "/favicon.ico" };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http // .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests(request -> request
				.antMatchers(AUTH_WHITELIST).permitAll() // Public URL
				.antMatchers("/admin").hasAuthority(Roles.ROLE_ADMIN.name())
				.anyRequest() //
				.authenticated());
		
		http.authorizeRequests(withDefaults())
			.formLogin(form -> form
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/j_spring_security_check") // The URL to submit the username and password to
				.successHandler(customLoginSuccessHandler)
				.defaultSuccessUrl("/")
				// .failureUrl("/login?error=true")
				.failureHandler(failureHandler()))
			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Csrf logout
				.logoutSuccessHandler(logoutSuccessHandler())
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"));

		http.authorizeRequests(withDefaults())
			.exceptionHandling(handling -> handling.accessDeniedPage("/403"));

        http.headers(headers -> headers
                .addHeaderWriter(new ClearSiteDataHeaderWriter( // 
                        Directive.CACHE, Directive.COOKIES, Directive.STORAGE)));
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)	// Cài đặt dịch vụ để tìm kiếm User trong Database
			.passwordEncoder(passwordEncoder());	// Cài đặt PasswordEncoder
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new CustomLoginFailureHandler();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	public SimpleAuthenticationFilter authenticationFilter() throws Exception {
//	    SimpleAuthenticationFilter filter = new SimpleAuthenticationFilter();
//	    filter.setAuthenticationManager(authenticationManagerBean());
//	    filter.setAuthenticationFailureHandler(failureHandler());
//	    return filter;
//	}

}
