package com.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private String username;
	private String password;
	private Set<String> roles;

	public User(String username, String password, String... roles) {
		this.username = username;
		this.password = password;
		this.roles = new HashSet<>();
		if (roles != null) {
			for (String r : roles) {
				this.roles.add(r);
			}
		}
	}

}
