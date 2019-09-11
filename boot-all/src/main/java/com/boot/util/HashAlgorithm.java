package com.boot.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashAlgorithm {

	public static String md5(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
		StringBuilder hash = new StringBuilder();
		for (byte b : bytes) {
			hash.append(String.format("%02x", b));
		}
		return hash.toString();
	}

	public static boolean matches(String text, String hashText) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(text, hashText);
	}

}
