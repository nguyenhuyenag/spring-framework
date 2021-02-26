package com.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
	
	/**
	 * Project directory
	 */
	public static final String HOME = System.getProperty("user.dir");
	
	public static final String RESOURCES = Paths.get(HOME, "src/main/resources").toString();
	
	/**
	 * Test whether a file or directory exists
	 * @param path the path to the file to test
	 * @return {@code true} if the file exists, otherwise {@code false}
	 */
	public static boolean exists(Path path) {
		return (path != null && Files.exists(path));
	}
	
}
