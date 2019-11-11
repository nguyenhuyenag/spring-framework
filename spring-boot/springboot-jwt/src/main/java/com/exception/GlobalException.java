package com.exception;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

	@Autowired
	DefaultErrorAttributes defaultError;

	// Hide exception field in the return object
	@Bean
	public ErrorAttributes errorAttributes() {
		return new ErrorAttributes() {
			@Override
			public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
				Map<String, Object> error = defaultError.getErrorAttributes(webRequest, includeStackTrace);
				error.remove("exception");
				return error;
			}

			@Override
			public Throwable getError(WebRequest webRequest) {
				return defaultError.getError(webRequest);
			}
		};
	}

	@ExceptionHandler(HandlerException.class)
	public void handleCustomException(HttpServletResponse res, HandlerException e) throws IOException {
		res.sendError(e.getHttpStatus().value(), e.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied GlobalException.java");
	}

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse res) throws IOException {
		res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Something went wrong GlobalException.java");
	}

}
