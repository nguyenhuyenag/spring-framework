package com.boot.configuration;

import lombok.Data;

@Data
public class SimpleBean {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SimpleBean(String username) {
		setUsername(username);
	}

}
