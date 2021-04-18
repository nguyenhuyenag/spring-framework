package com.models;

public class AccountModel {
	
	public boolean login(String username, String password) {
		return username.equalsIgnoreCase("abc") && password.equalsIgnoreCase("123");
	}
	
}
