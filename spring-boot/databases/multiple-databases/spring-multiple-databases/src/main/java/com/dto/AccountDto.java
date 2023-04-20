package com.dto;

public class AccountDto {

	private String username;
	private String fullName;
	private String email;
	private String password;
	private String rePassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public AccountDto(String username, String fullName, String email, String password, String rePassword) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.rePassword = rePassword;
	}

	public AccountDto() {
		super();
	}

}
