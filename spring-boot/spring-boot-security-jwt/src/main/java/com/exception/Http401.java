package com.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.DateTimeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class Http401 implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -5775441819243889992L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		response.setStatus(401);
		response.setContentType("application/json;charset=UTF-8");
    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, String> map = new HashMap<>();
		map.put("timestamp", DateTimeUtils.getNow());
		map.put("status", "401");
		map.put("error", "Unauthorized");
		map.put("path", "");
    	response.getWriter().write(mapper.writeValueAsString(map));
	}
	
}