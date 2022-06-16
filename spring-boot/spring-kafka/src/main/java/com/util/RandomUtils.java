package com.util;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
	
	public static int randomInteger(int min, int max) {
		if (max <= min) {
			throw new IllegalArgumentException("Max must be greater than min");
		}
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public static String initCode() {
		return "V" + 2022 + RandomStringUtils.randomAlphabetic(40).toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(initCode());
	}
	
}
