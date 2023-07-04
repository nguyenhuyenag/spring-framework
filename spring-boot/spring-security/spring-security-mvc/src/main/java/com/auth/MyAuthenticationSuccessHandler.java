package com.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
//		String targetUrl = determineTargetUrl(authentication);
//		// String targetUrl = super.determineTargetUrl(request, response);
//		if (response.isCommitted()) {
//			LOG.debug("Response has already been committed. Unable to redirect to: " + targetUrl);
//			return;
//		}
//		redirectStrategy.sendRedirect(request, response, targetUrl);
		String targetUrl = "";
		Object savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		if (savedRequest instanceof DefaultSavedRequest) {
			DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
			// String fullContextPath2 = request.getRequestURL().toString();
			// System.out.println("[AuthController] fullContextPath: " + fullContextPath2);
			if (!"/".equals(defaultSavedRequest.getRequestURI())) {
				LOG.info("Redirect from: {}", defaultSavedRequest.getRedirectUrl());
				// String nextId = WebUtils.setNextPage(defaultSavedRequest.getRedirectUrl());
				targetUrl = defaultSavedRequest.getRedirectUrl();
			}
		}
		
		// This will provide you last URL
		// String targetUrl = request.getHeader("referer");

		if (response.isCommitted()) {
			LOG.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		
		// If targetUrl = null ?
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

//	protected String determineTargetUrl(final Authentication authentication) {
//		Map<String, String> roleTargetUrlMap = new HashMap<>();
//		roleTargetUrlMap.put("ROLE_USER", "/homepage.html");
//		roleTargetUrlMap.put("ROLE_ADMIN", "/console.html");
//		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		for (final GrantedAuthority grantedAuthority : authorities) {
//			String authorityName = grantedAuthority.getAuthority();
//			if (roleTargetUrlMap.containsKey(authorityName)) {
//				return roleTargetUrlMap.get(authorityName);
//			}
//		}
//		throw new IllegalStateException();
//	}

}

// (2)
//public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//	//protected Logger LOG = LoggerFactory.getLogger(this.getClass());
//	private final GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//
//	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//
//		// if redirected from some specific url, need to remove the cachedRequest to
//		// force use defaultTargetUrl
//		// final RequestCache requestCache = new HttpSessionRequestCache();
//		// final SavedRequest savedRequest = requestCache.getRequest(request, response);
//		// System.out.println("GetRedirectUrl: "+savedRequest.getRedirectUrl());
//		if (!isAdminAuthority(authentication)) {
//			String targetUrl = super.determineTargetUrl(request, response);
//			// this logic is only for demo purpose, please do not use it on production
//			// application.
//			if (StringUtils.isBlank(targetUrl) || StringUtils.equals(targetUrl, "/")) {
//				targetUrl = "/home"; // we can build custom logic
//			}
//			super.clearAuthenticationAttributes(request);
//			//LOG.info("Redirecting customer to the following location {} ", targetUrl);
//			redirectStrategy.sendRedirect(request, response, targetUrl);
//
//			// You can let Spring security handle it for you.
//			// super.onAuthenticationSuccess(request, response, authentication);
//
//		} else {
//			// we invalidating the session for the admin user.
//			invalidateSession(request, response);
//		}
//		super.clearAuthenticationAttributes(request);
//	}
//
//	protected void invalidateSession(final HttpServletRequest request, final HttpServletResponse response)
//			throws IOException {
//		SecurityContextHolder.getContext().setAuthentication(null);
//		request.getSession().invalidate();
//		redirectStrategy.sendRedirect(request, response, "/admin");
//	}
//
//	protected boolean isAdminAuthority(final Authentication authentication) {
//		return !CollectionUtils.isEmpty(authentication.getAuthorities()) //
//				&& authentication.getAuthorities().contains(adminAuthority);
//	}
//	
//}