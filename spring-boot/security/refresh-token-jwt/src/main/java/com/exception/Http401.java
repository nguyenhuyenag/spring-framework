package com.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.payload.reponse.ErrorResponse;
import com.util.JsonUtils;

@Component
public class Http401 implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed)
			throws IOException {
		res.setStatus(401);
		res.setContentType("application/json;charset=UTF-8");
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(401);
		error.setError("Unauthorized");
		error.setMessage("From Http401");
		error.setPath(req.getRequestURI());
		
		res.getWriter().write(JsonUtils.toJSON(error));
	}

}