package com.reponse;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "type", "token" }) // sort field
public class LoginResponse {

	private String token;
	private String type = "Bearer";

	public LoginResponse() {

	}

	public LoginResponse(String token) {
		this.token = token;
	}

}
