package com.payload.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {

	@NotBlank
	@JsonProperty("refresh_token")
	private String refreshToken;

//  public String getRefreshToken() {
//    return refreshToken;
//  }
//
//  public void setRefreshToken(String refreshToken) {
//    this.refreshToken = refreshToken;
//  }
}