package com.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.reponse.ErrorResponse;
import com.util.JsonUtils;

public class Http403 implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
    	// response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
    	response.setStatus(403);
		response.setContentType("application/json;charset=UTF-8");
		ErrorResponse error = new ErrorResponse();
		error.setStatus(403);
		error.setError("Access denieddddd");
		error.setMessage("");
		error.setPath(request.getRequestURI());
    	response.getWriter().write(JsonUtils.toJSON(error));
    }

}
