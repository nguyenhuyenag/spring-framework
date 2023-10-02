package com.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth.CustomLogoutHandler;
import com.auth.LoginFailureHandler;
import com.util.Roles;

@Configuration
@EnableWebSecurity // (debug = true)
public class InMemoryAuthWebSecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private UserDetailsService userDetailsService;
	
	private final String[] AUTH_WHITELIST = { "/static/**", "/login", "/logout", "/favicon.ico" };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(request -> request
				.antMatchers(AUTH_WHITELIST).permitAll() // Public URL
				.antMatchers("/admin").hasAuthority(Roles.ROLE_ADMIN.name())
				// .antMatchers("/auth/admin/*").hasAuthority("ADMIN")
			    // .antMatchers("/auth/*").hasAnyAuthority("ADMIN", "USER")
				.anyRequest().authenticated());

		http.authorizeRequests(withDefaults())
			.formLogin(form -> form
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/j_spring_security_check") // The URL to submit the username and password to
				// .successHandler(successHandler())
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				.failureHandler(failureHandler()))
			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Csrf logout
				.logoutSuccessHandler(logoutSuccessHandler())
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"));

		http.authorizeRequests(withDefaults())
			.exceptionHandling(handling -> handling.accessDeniedPage("/403"));
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)	// Cài đặt dịch vụ để tìm kiếm User trong Database
//			.passwordEncoder(passwordEncoder());	// Cài đặt PasswordEncoder
//	}
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.withUsername("admin")
			     .password(passwordEncoder().encode("123456"))
			     .roles("ADMIN")
			     .build();
		
		UserDetails user = User.withUsername("user1")
			     .password(passwordEncoder().encode("123456"))
			     .roles("USER")
			     .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth .userDetailsService(userDetailsService())
			// .inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
//			.withUser("admin")
//				.password(passwordEncoder().encode("123456"))
//				.roles("ADMIN")
//				.and()
//			.withUser("user1")
//				.password(passwordEncoder().encode("123456"))
//				.roles("USER")
//				.and()
			// .withUser("no_password_encode")
			//	.password("{noop}1234567")
			//	.roles("USER")
			;
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new LoginFailureHandler();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
