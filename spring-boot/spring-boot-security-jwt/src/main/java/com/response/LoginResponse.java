package com.response;

import com.filter.TokenHandler;

public class LoginResponse {

	private String token;

	public LoginResponse() {

	}

	public LoginResponse(String token) {
		this.token = TokenHandler.TOKEN_PREFIX + token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
