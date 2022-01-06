package com.exception;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.reponse.ErrorResponse;
import com.util.JsonUtils;

@Component
public class Http401 implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -5775441819243889992L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		response.setStatus(401);
		response.setContentType("application/json;charset=UTF-8");
		ErrorResponse error = new ErrorResponse();
		error.setStatus(401);
		error.setError("Unauthorized");
		error.setMessage("From Http401.class");
		error.setPath(request.getRequestURI());
    	response.getWriter().write(JsonUtils.toJSON(error));
	}
	
}