package jwt.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private JwtProvider jwtProvider;

	public JwtFilterConfigurer(final JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtFilter filter = new JwtFilter(jwtProvider);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

}
