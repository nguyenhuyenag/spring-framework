//package com.auth;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//
//	@Override
//	public void handle(HttpServletRequest request, HttpServletResponse response,
//			AccessDeniedException accessDeniedException) throws IOException, ServletException {
//		// Log thông báo lỗi CSRF ra console
//		System.err.println("CSRF Error: " + accessDeniedException.getMessage());
//
//		// Điều hướng người dùng đến trang lỗi CSRF hoặc trang khác tùy ý
//		response.sendRedirect("/csrf-error");
//	}
//}
