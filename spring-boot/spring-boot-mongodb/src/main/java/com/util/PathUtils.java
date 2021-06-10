package com.util;

import java.nio.file.Files;
import java.nio.file.Path;

public class PathUtils {

	/**
	 * Test whether a file or directory exists
	 */
	public static boolean exist(Path path) {
		return (path != null && Files.exists(path));
	}

	public static boolean isNotExist(Path path) {
		return !exist(path);
	}

}
