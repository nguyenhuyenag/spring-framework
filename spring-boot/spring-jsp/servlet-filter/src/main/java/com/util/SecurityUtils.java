package com.util;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.config.SecurityConfig;

public class SecurityUtils {

	public static boolean isProtectedPage(String servletPath) {
		Set<String> set = SecurityConfig.listProtectedPage();
		return set.contains(servletPath);
	}

	// Kiểm tra 'request' này có vai trò phù hợp hay không?
	public static boolean hasPermission(HttpServletRequest request) {
		String url = request.getServletPath();
		Set<String> listRoles = SecurityConfig.getAllAppRoles();
		for (String role : listRoles) {
			if (request.isUserInRole(role)) {
				List<String> urlsByRole = SecurityConfig.getUrlPatternsForRole(role);
				if (urlsByRole != null && urlsByRole.contains(url)) {
					return true;
				}
			}
		}
		return false;
	}

}
