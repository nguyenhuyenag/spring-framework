package com.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Base64Utils {

//	public static byte[] encode(byte[] byteArr) {
//		return Base64.getEncoder().encode(byteArr);
//	}

	public static String encodeToString(byte[] byteArr) {
		byte[] arr = Base64.getEncoder().encode(byteArr);
		return new String(arr, StandardCharsets.UTF_8);
	}

//	public static String encodeToString(String str) {
//		byte[] byteArr = str.getBytes(StandardCharsets.UTF_8);
//		return encodeToString(byteArr);
//	}

	public static String encodeToString(Path path) {
		try {
			byte[] byteArr = Files.readAllBytes(path);
			byteArr = Base64.getEncoder().encode(byteArr);
			return new String(byteArr, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static byte[] decodeToByte(String base64) {
		byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
		byteArr = Base64.getDecoder().decode(byteArr);
		return byteArr;
	}

//	public static String decodeToString(String base64) {
//		byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
//		byteArr = Base64.getDecoder().decode(byteArr);
//		return new String(byteArr);
//	}

}
