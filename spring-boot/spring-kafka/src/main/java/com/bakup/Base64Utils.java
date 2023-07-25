package com.bakup;

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

	public static String decodeToString(String base64) {
		byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
		byteArr = Base64.getDecoder().decode(byteArr);
		return new String(byteArr);
	}

}
