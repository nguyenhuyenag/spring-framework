//package com.filter;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
///**
// * server.servlet.session.cookie.secure=true
// * server.servlet.session.cookie.http-only=true
// */
//@Component
//public class SessionFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		Cookie[] allCookies = req.getCookies();
//		if (allCookies != null) {
//			Cookie session = Arrays.stream(allCookies) //
//					.filter(x -> x.getName().equals("JSESSIONID")) //
//					.findFirst() //
//					.orElse(null);
//
//			if (session != null) {
//				session.setHttpOnly(true);
//				session.setSecure(true);
//				res.addCookie(session);
//			}
//		}
//		chain.doFilter(req, res);
//	}
//}
