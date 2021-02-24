package com.util;

import java.nio.file.Paths;

public class PathUtils {
	
	/**
	 * Project directory
	 */
	public static final String HOME = System.getProperty("user.dir");
	
	public static final String RESOURCES = Paths.get(HOME, "src/main/resources").toString();
	
}
