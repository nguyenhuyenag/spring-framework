//package com.config;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.savedrequest.DefaultSavedRequest;
//
//public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
//		implements AuthenticationSuccessHandler {
//
//	public CustomAuthenticationSuccessHandler() {
//		super();
//		setUseReferer(true);
//	}
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession()
//				.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//		if (defaultSavedRequest != null) {
//			getRedirectStrategy().sendRedirect(request, response, defaultSavedRequest.getRedirectUrl());
//		} else {
//			super.onAuthenticationSuccess(request, response, authentication);
//		}
//	}
//
////	public static final String REDIRECT_URL_SESSION_ATTRIBUTE_NAME = "REDIRECT_URL";
////
////	public CustomAuthenticationSuccessHandler() {
////		super();
////		setUseReferer(true);
////	}
////
////	@Override
////	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
////			Authentication authentication) throws IOException, ServletException {
////		Object redirectURLObject = request.getSession().getAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME);
////		if (redirectURLObject != null)
////			setDefaultTargetUrl(redirectURLObject.toString());
////		else {
////			setDefaultTargetUrl("/");
////		}
////		request.getSession().removeAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME);
////		super.onAuthenticationSuccess(request, response, authentication);
////	}
//
//}