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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.entity.RefreshToken;
import com.payload.reponse.ErrorResponse;
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
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		SecurityContextHolder.getContext().setAuthentication(authRequest);
		// Authentication authentication = AuthenticationService.getAuthentication(req);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override // Login successful
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// String json = createJWTResponse(authResult);
		JwtResponse jwtResponse = createJWTResponse(authResult);
		
		res.setHeader("Authorization", jwtResponse.getAccessToken());
		res.getWriter().write(JsonUtils.toJSON(jwtResponse));
	}

	@Override // Login fail
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException failed) throws IOException, ServletException {
		// if user is disable status = 403
		ErrorResponse error = new ErrorResponse();
		// error.setStatus(status);
		error.setError("unsuccessfulAuthentication");
		error.setMessage("JWTAuthenticationFilter: " + failed.getMessage());
		error.setPath(req.getRequestURI());
		String json = JsonUtils.toJSON(error);
		res.getWriter().write(json);
	}

	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest req) throws IOException {
		LoginRequest login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
		if (login == null) {
			throw new IllegalArgumentException("The login object is missing or invalid");
		}
		return new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
	}
	
	private JwtResponse createJWTResponse(Authentication auth) {
		String authorities = getStringAuthorities(auth);
		String jwt = TokenHandler.createJWT(auth.getName(), authorities);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(auth.getName());
		return new JwtResponse(jwt, refreshToken.getToken());
	}

//	private String createJWTResponse(Authentication auth) {
//		String authorities = getStringAuthorities(auth);
//		String jwt = TokenHandler.createJWT(auth.getName(), authorities);
//		RefreshToken refreshToken = refreshTokenService.createRefreshToken(auth.getName());
//		return JsonUtils.toJSON(new JwtResponse(jwt, refreshToken.getToken()));
//	}

	// auth.getAuthorities() -> never null
	private String getStringAuthorities(Authentication auth) {
		StringJoiner authorities = new StringJoiner(",");
		auth.getAuthorities().stream().forEach(t -> {
			authorities.add(t.getAuthority());
		});
		return authorities.toString();
	}

}
