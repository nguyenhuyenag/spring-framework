package com.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.response.CustomError;
import com.util.JsonUtils;

public class Http403Forbidden implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e)
			throws IOException, ServletException {
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		CustomError error = new CustomError(403, "Forbidden", "Access Denied");
		String json = JsonUtils.writeAsString(error);
		res.getWriter().write(json);
	}

}
