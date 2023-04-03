package com.service.impl;

import org.springframework.stereotype.Service;

import com.service.AuthService;
import com.util.TokenHandler;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public boolean checkToken(String token) {
		try {
			return TokenHandler.isAlive(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
