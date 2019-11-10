package com.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.util.UrlPathHelper;

import com.entity.User;
import com.request.LoginRequest;
import com.response.UserResponse;
import com.service.TokenAuthService;
import com.service.impl.TokenAuthServiceImpl;
import com.util.JSONUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTLoginFilter.class);
	
	private static final UrlPathHelper URL = new UrlPathHelper();

	private TokenAuthService tokenAuthService = new TokenAuthServiceImpl();

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		LoginRequest login = JSONUtils.OBJECT_MAPPER.readValue(request.getInputStream(), LoginRequest.class);
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		UserResponse object = new UserResponse(user.getUsername(), user.getRole());
		String jsonObject = JSONUtils.objectToJSON(object);
		response.getWriter().write(jsonObject);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		tokenAuthService.addAuthentication(response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		LOGGER.info("Failed authentication while attempting to access " + URL.getPathWithinApplication(request));
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Res res = new Res(HttpServletResponse.SC_UNAUTHORIZED, "Username or password is incorrect!");
		String jsonObject = JSONUtils.objectToJSON(res);
		response.getWriter().write(jsonObject);
		response.getWriter().flush();
	}

}

class Res {

	private Integer status;
	private String des;

	public Res() {
	}

	public Res(Integer status, String des) {
		this.status = status;
		this.des = des;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
