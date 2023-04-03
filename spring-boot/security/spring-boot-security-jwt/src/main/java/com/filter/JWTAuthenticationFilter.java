package com.filter;

import java.io.IOException;
import java.util.StringJoiner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.entity.RefreshToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.payload.reponse.JwtResponse;
import com.payload.request.LoginRequest;
import com.service.RefreshTokenService;
import com.util.JsonUtils;
import com.util.TokenHandler;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private RefreshTokenService refreshTokenService;

	public JWTAuthenticationFilter(AuthenticationManager am, RefreshTokenService refreshTokenService) {
		super(new AntPathRequestMatcher("/auth/login"));
		this.setAuthenticationManager(am);
		this.refreshTokenService = refreshTokenService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws JsonParseException, JsonMappingException, IOException {
		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(req);
		SecurityContextHolder.getContext().setAuthentication(authRequest);
		return getAuthenticationManager().authenticate(authRequest);
	}

	// Login successful
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String json = createJWTResponse(authResult);
		res.getWriter().write(json);
		// res.addHeader(HttpHeaders.AUTHORIZATION, TokenHandler.PREFIX + jwt);
	}

	// Login fail
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
//			AuthenticationException failed) throws IOException, ServletException {
//		// if user is disable status = 403
//		String message = failed.getMessage();
//		// res.setStatus(status);
//		ErrorResponse error = new ErrorResponse();
//		// error.setStatus(status);
//		error.setError("unsuccessfulAuthentication");
//		error.setMessage("From JWTAuthenticationFilter: " + message);
//		error.setPath(req.getRequestURI());
//		String json = JsonUtils.toJSON(error);
//		res.getWriter().write(json);
//	}

	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest req) throws IOException {
		LoginRequest login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
		return new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
	}

	private String createJWTResponse(Authentication authResult) {
		String authorities = getStringAuthorities(authResult);
		String jwt = TokenHandler.createJWT(authResult.getName(), authorities);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(authResult.getName());
		return JsonUtils.toJSON(new JwtResponse(jwt, refreshToken.getToken()));
	}

	// auth.getAuthorities() never null
	private String getStringAuthorities(Authentication auth) {
		StringJoiner authorities = new StringJoiner(",");
		auth.getAuthorities().stream().forEach(t -> {
			authorities.add(t.getAuthority());
		});
		return authorities.toString();
	}

}
