package com.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.response.ApiError;
import com.util.JsonUtils;

public class Http403Forbidden implements AccessDeniedHandler {

	private static final Logger LOG = LoggerFactory.getLogger(Http403Forbidden.class);

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		String url = req.getRequestURI();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			LOG.warn("Account `" + auth.getName() + "` attempted to access the protected URL: " + url);
		}
		String json = JsonUtils.toJSON(new ApiError(403, "Forbidden", "Access Denied", url));
		res.getWriter().write(json);
	}

}
