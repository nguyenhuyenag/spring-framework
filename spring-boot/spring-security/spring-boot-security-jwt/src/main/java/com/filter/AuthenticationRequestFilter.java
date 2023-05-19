package com.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.config.WebSecurityConfig;
import com.util.TokenHandler;

// @Component
public class AuthenticationRequestFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationRequestFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

	public AuthenticationRequestFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		res.setCharacterEncoding("UTF-8");
		res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

//	    final String param = Optional.ofNullable(req.getHeader(HttpHeaders.AUTHORIZATION))
//	            .orElse(req.getParameter("t"));
//		final String token = Optional.ofNullable(param)
//	            .map(value -> StringUtils.removeStart(value, TokenHandler.BEARER))
//	            .map(String::trim)
//	            .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));

		if (inAntMatcher(req.getRequestURI())) {
			LOG.info("Request '{}' is in white list", req.getRequestURI());
			chain.doFilter(req, res);
			return;
		}

		LOG.info("Filter request '{}'", req.getRequestURI());
		String jwt = extractJWT(req);
		if (StringUtils.isEmpty(jwt)) {
			LOG.info("Couldn't find bearer string");
			throw new BadCredentialsException("Missing authentication token");
		}
		
		DecodedJWT verify = TokenHandler.verifyJWT(jwt);
		if (verify == null) {
			throw new JWTDecodeException("Invalid JWT token");
		}
		
		DecodedJWT decoded = TokenHandler.decodedJWT(jwt);
		String username = TokenHandler.getSubject(decoded);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null && StringUtils.isNotEmpty(username)) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authToken = getAuthentication(userDetails, decoded);
			authToken.setDetails(new WebAuthenticationDetailsSource() //
					 .buildDetails(req));
			LOG.info("Authenticated '" + username + "', setting security context");
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(UserDetails userDetails, DecodedJWT decoded) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		Claim claims = TokenHandler.getClaim(decoded);
		if (StringUtils.isNotEmpty(claims.asString())) {
			Arrays.stream(claims.asString().split(",")).forEach(t -> {
				authorities.add(new SimpleGrantedAuthority(t));
			});
		}
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	private boolean inAntMatcher(String path) {
		for (String pattern : WebSecurityConfig.WHITE_LIST) {
			if (antPathMatcher.match(pattern, path)) {
				return true;
			}
		}
		return false;
	}

	private String extractJWT(HttpServletRequest req) {
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith(TokenHandler.BEARER)) {
			return header.replace(TokenHandler.BEARER, "");
		}
		return "";
	}

}
