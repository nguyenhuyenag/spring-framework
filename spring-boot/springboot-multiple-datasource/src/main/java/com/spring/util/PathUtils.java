package com.spring.util;

import java.nio.file.Paths;

public class PathUtils {

	public static String getUserDir() {
		return System.getProperty("user.dir");
	}

	public static String getPathResources() {
		return Paths.get(getUserDir(), "src/main/resources").toString();
	}

	public static String getPathUpload() {
		return Paths.get(getPathResources(), "upload").toString();
	}

}
