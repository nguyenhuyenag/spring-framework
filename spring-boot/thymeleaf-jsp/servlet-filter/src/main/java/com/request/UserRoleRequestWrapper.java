package com.request;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

	private String username;
	private Set<String> roles = null;
	private HttpServletRequest realRequest;

	public UserRoleRequestWrapper(HttpServletRequest request, String username, Set<String> roles) {
		super(request);
		this.roles = roles;
		this.username = username;
		this.realRequest = request;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (this.roles == null) {
			return this.realRequest.isUserInRole(role);
		}
		return roles.contains(role);
	}

	@Override
	public Principal getUserPrincipal() {
		if (this.username == null) {
			return this.realRequest.getUserPrincipal();
		}
		// make an anonymous implementation to just return our user
		return new Principal() {
			@Override
			public String getName() {
				return username;
			}
		};
	}

}
