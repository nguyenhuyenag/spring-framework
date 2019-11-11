package com.of.configuration;

import lombok.Data;

@Data
public class SimpleBean {

	private String username;

	public SimpleBean(String username) {
		setUsername(username);
	}

}
