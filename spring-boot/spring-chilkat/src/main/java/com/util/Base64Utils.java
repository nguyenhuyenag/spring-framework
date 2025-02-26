package com.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class Base64Utils {

//	public static byte[] encode(byte[] byteArr) {
//		return Base64.getEncoder().encode(byteArr);
//	}

    public static String encodeToString(byte[] byteArr) {
        byte[] arr = Base64.getEncoder().encode(byteArr);
        return new String(arr, StandardCharsets.UTF_8);
    }

    public static String encodeToString(String input) {
        if (input != null) {
            return encodeToString(input.getBytes(StandardCharsets.UTF_8));
        }
        return "";
    }

    //	public static String encodeToString(Path path) {
//		try {
//			byte[] byteArr = Files.readAllBytes(path);
//			byteArr = Base64.getEncoder().encode(byteArr);
//			return new String(byteArr);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

    public static byte[] decodeToByte(String base64) {
        byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
        return Base64.getDecoder().decode(byteArr);
    }

    public static String decodeToString(String base64) {
        try {
            byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
            byteArr = Base64.getDecoder().decode(byteArr);
            return new String(byteArr);
        } catch (IllegalArgumentException e) {
            log.error("Failed to decode the Base64 string: {}.", base64);
        }
        return "";
    }

//	public static Path base64ToFile(Path path, String base64) {
//		byte[] byteArr = Base64Utils.decodeToByte(base64);
//		if (byteArr == null) {
//			throw new IllegalArgumentException("Illegal character in Base64 data");
//		}
//		return FileUtils.writeByteArrayToFile(path, byteArr, false);
//	}

}
