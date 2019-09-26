package com.boot.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 5907023648091540313L;

	// 401
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

	// @ExceptionHandler(value = { AccessDeniedException.class })
	// public void commence(HttpServletRequest request, HttpServletResponse
	// response, AccessDeniedException accessDeniedException) throws IOException {
	// // 403
	// response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed :
	// " + accessDeniedException.getMessage());
	// }
	//
	// @ExceptionHandler(value = { Exception.class })
	// public void commence(HttpServletRequest request, HttpServletResponse
	// response, Exception exception) throws IOException {
	// // 500
	// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal
	// Server Error : " + exception.getMessage());
	// }

}
