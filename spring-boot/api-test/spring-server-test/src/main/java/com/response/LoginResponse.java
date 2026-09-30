package com.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// @AllArgsConstructor
public class LoginResponse {

	private String tokenType = "Bearer";
	private String token;

	public LoginResponse(String token) {
		this.token = token;
	}

}
