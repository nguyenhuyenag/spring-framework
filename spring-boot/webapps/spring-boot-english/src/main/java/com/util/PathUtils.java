package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathUtils {

	// File.separator => '\'

	/**
	 * Project directory
	 */
	public static final String HOME = System.getProperty("user.dir");

	/**
	 * Test whether a file or directory exists
	 */
	public static boolean exist(Path path) {
		return (path != null && Files.exists(path));
	}
	
	public static boolean isNotExist(Path path) {
		return !exist(path);
	}

	/**
	 * Tạo thư mục
	 */
	public static boolean createDirectories(Path dir) {
		try {
			Files.createDirectories(dir);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
