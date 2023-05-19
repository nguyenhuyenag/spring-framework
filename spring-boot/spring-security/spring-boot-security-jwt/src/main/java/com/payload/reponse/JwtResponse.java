package com.payload.reponse;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "type", "refresh_token", "access_token" }) // sort field
public class JwtResponse {

	private String type = "Bearer";
	private String access_token;
	private String refresh_token;

	public JwtResponse(String token, String refreshToken) {
		this.access_token = token;
		this.refresh_token = refreshToken;
	}

}
