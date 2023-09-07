package com.util;

import java.util.Collection;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class WebUtils {

	protected static Logger LOG = LoggerFactory.getLogger(WebUtils.class);

	// @Autowired
	// private HttpServletRequest request;

//	private static HttpServletRequest dependency;
//
//	public WebUtils(HttpServletRequest dependency) {
//		WebUtils.dependency = dependency;
//	}

	public static String toString(UserDetails user) {
		StringBuilder builder = new StringBuilder();
		builder.append("Username: ") //
			   .append(user.getUsername()) //
			   .append(", Role: ");
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		for (GrantedAuthority grant : authorities) {
			sj.add(grant.toString());
		}
		builder.append(sj.toString());
		// Or using: StringUtils.join(authorities, ",");
		return builder.toString();
	}

}
