package com.boot.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 5907023648091540313L;

	/**
	 * Always returns a 401 error code to the client
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, //
			AuthenticationException authException) throws IOException, ServletException {
		// voi cac request khong xac thuc thanh cong, du lieu se duoc tra ve o day
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		//response.getWriter().println("HTTP Status 401 - " + authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		response.getWriter().flush();
	}

}
