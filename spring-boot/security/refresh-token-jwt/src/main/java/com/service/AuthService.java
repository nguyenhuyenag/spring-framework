package com.service;

import org.springframework.stereotype.Service;

import com.util.TokenHandler;

@Service
public class AuthService {

	public boolean checkToken(String token) {
		return TokenHandler.isAlive(token);
	}

}
