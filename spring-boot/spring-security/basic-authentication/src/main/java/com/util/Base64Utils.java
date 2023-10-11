package com.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

	public static String encodeToString(byte[] byteArr) {
		byte[] arr = Base64.getEncoder().encode(byteArr);
		return new String(arr, StandardCharsets.UTF_8);
	}

	public static String encodeToString(String str) {
		byte[] byteArr = str.getBytes(StandardCharsets.UTF_8);
		return encodeToString(byteArr);
	}

	public static void base64Authen(String username, String password) {
		String encoding = encodeToString(username + ":" + password);
		String authHeader = "Basic " + encoding;
		System.out.println(authHeader);
	}

}
