package com.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public boolean checkToken(String token) {
		return true; // TokenHandler.isAlive(token);
	}

}
