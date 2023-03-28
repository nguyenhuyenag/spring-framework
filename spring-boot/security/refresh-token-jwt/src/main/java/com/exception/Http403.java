package com.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.payload.reponse.ErrorResponse;
import com.util.JsonUtils;

public class Http403 implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException failed)
			throws IOException, ServletException {
		res.setStatus(403);
		res.setContentType("application/json;charset=UTF-8");
		ErrorResponse error = new ErrorResponse();
		error.setStatus(403);
		error.setError("Access denied");
		error.setMessage("From Http403");
		error.setPath(req.getRequestURI());
		res.getWriter().write(JsonUtils.toJSON(error));
	}

}
