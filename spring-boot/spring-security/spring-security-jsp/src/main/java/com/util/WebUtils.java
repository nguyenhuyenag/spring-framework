package com.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class WebUtils {

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

}
