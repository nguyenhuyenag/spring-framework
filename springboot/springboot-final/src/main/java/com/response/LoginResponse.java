package com.response;

public class LoginResponse {

	private String role;
	private String username;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LoginResponse() {
		super();
	}

	public LoginResponse(String role, String username) {
		this.role = role;
		this.username = username;
	}

}
