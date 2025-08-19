package com.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;

	public JwtResponse(String token, String refreshToken) {
		this.accessToken = token;
		this.refreshToken = refreshToken;
	}

}
