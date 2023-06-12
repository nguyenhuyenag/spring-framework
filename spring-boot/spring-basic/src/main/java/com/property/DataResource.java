package com.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResource {

	private String driverClassName;
	private String url;
	private String username;
	private String password;

	public void printConnection() {
		System.out.println("url: " + this.url + "\n" + "username/password: " + this.username + "/" + this.password);
	}
}
