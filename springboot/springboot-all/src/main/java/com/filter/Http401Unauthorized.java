package com.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.response.CustomError;
import com.util.JsonUtils;

@Component
public class Http401Unauthorized implements AuthenticationEntryPoint {

	private static final Logger LOG = LoggerFactory.getLogger(Http401Unauthorized.class);

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		LOG.info("401: Unauthorized");
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		CustomError error = new CustomError(401, "Unauthorized", "The username or password is incorrect");
		String json = JsonUtils.writeAsString(error);
		res.getWriter().write(json);
		// res.addHeader("WWW-Authenticate", "Basic real ");
		// res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// PrintWriter writer = res.getWriter();
		// writer.println("HTTP Status 401 - " + e.getMessage());
	}

}
