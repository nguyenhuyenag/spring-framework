package com.reponse;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "type", "refresh_token", "access_token" }) // sort field
public class LoginResponse {

	private String type = "Bearer";
	private String access_token;
	// private String refresh_token;

	public LoginResponse() {

	}

	public LoginResponse(String token) {
		this.access_token = token;
	}

}
