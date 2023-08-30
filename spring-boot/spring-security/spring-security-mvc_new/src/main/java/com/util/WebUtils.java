package com.util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

@Component
public class WebUtils {

	// private static final Map<String, String> NEXT_PAGE = new HashMap<>();
	
	protected static Logger LOG = LoggerFactory.getLogger(WebUtils.class);

	// @Autowired
	HttpServletRequest request;

	private static HttpServletRequest dependency;

	public WebUtils(HttpServletRequest dependency) {
		WebUtils.dependency = dependency;
	}

	public static String toString(UserDetails user) {
		StringBuilder builder = new StringBuilder();
		builder.append("Username: ").append(user.getUsername());
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		if (authorities != null && !authorities.isEmpty()) {
			builder.append(" [");
			boolean first = true;
			for (GrantedAuthority a : authorities) {
				if (first) {
					builder.append(a.getAuthority());
					first = false;
				} else {
					builder.append(", ").append(a.getAuthority());
				}
			}
			builder.append("]");
		}
		return builder.toString();
	}

	public static String getRedirectUrl() {
		Object savedRequest = dependency.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		if (savedRequest instanceof DefaultSavedRequest) {
			DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
			// String fullContextPath2 = request.getRequestURL().toString();
			// System.out.println("[AuthController] fullContextPath: " + fullContextPath2);
			if (!"/".equals(defaultSavedRequest.getRequestURI())) {
				LOG.info("Redirect from: {}", defaultSavedRequest.getRedirectUrl());
				return defaultSavedRequest.getRedirectUrl();
			}
		}
		return "";
	}

//	public static String setNextPage(String url) {
//		if (StringUtils.isNotEmpty(url)) {
//			try {
//				String nextId = UUID.randomUUID().toString();
//				NEXT_PAGE.put(nextId, URLDecoder.decode(url, StandardCharsets.UTF_8.toString()));
//				return nextId;
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
//		return "";
//	}

}
