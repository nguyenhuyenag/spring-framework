package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class WebUtils {

	private static final Map<String, String> NEXT_PAGE = new HashMap<>();

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

	public static String setNextPage(String url) {
		if (StringUtils.isNotEmpty(url)) {
			try {
				String nextId = UUID.randomUUID().toString();
				NEXT_PAGE.put(nextId, URLDecoder.decode(url, StandardCharsets.UTF_8.toString()));
				return nextId;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

}
