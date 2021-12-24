package com.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.DateTimeUtils;

public class Http403 implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
    	// response.sendError(HttpServletResponse.SC_FORBIDDEN,"Access denied");
    	response.setContentType("application/json;charset=UTF-8");
    	response.setStatus(403);
    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, String> map = new HashMap<>();
		map.put("timestamp", DateTimeUtils.getNow());
		map.put("status", "403");
		map.put("error", "Access denied");
		map.put("path", "");
    	response.getWriter().write(mapper.writeValueAsString(map));
    }

}
