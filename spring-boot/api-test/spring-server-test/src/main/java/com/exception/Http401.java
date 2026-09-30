package com.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.util.JsonUtils;

@Component
public class Http401 implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed)
			throws IOException {

		res.setStatus(401);
		res.setContentType("application/json;charset=UTF-8");

		Map<String, Object> map = new HashMap<>();
		map.put("status", 401);
		map.put("error", "Unauthorized");
		map.put("message", "From Http401");
		map.put("path", req.getRequestURI());

		res.getWriter().write(JsonUtils.toJSON(map));
	}

}