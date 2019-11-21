package com.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.response.CustomError;
import com.util.JsonUtils;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		// HttpStatus.UNAUTHORIZED.name(), HttpServletResponse.SC_UNAUTHORIZED
		CustomError error = new CustomError(401, "Unauthorized", "Username or password is incorrect!");
		String json = JsonUtils.writeAsString(error);
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		res.getWriter().write(json);
	}

}
