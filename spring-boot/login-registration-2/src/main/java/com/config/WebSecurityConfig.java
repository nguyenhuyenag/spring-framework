package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.util.MD5PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private LoginFailureHandler loginFailureHandler;
	
	@Autowired
    private LoginSuccessHandler loginSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new MD5PasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService) // cài đặt dịch vụ để tìm kiếm User trong Database
			.passwordEncoder(passwordEncoder()); 	// cài đặt PasswordEncoder
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// các trang không yêu cầu login
		http.authorizeRequests()
			.antMatchers("/", "/login", "/logout", "/j_spring_security_check", "/static/**").permitAll()
		 	.anyRequest().authenticated();
		http.authorizeRequests().and()
			.formLogin() // cấu hình cho login
				.loginProcessingUrl("/j_spring_security_check") // submit URL
				.loginPage("/login") //
				.usernameParameter("username") //
				.passwordParameter("password")
				// .permitAll()
				.defaultSuccessUrl("/tracuu") //
				// .failureUrl("/login?error=true") //
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler).and() // 
			.logout() // cấu hình logout
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");
	}

}
