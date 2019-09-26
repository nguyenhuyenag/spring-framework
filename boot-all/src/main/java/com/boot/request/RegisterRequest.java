package com.boot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {

	private String username;
	private String fullName;
	private String email;
	private String password;
	private String passwordConfirm;

}
