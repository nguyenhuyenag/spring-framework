package com.reponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String token;
	private String type = "Bearer";

	public LoginResponse() {

	}

	public LoginResponse(String token) {
		this.token = token;
	}

}
