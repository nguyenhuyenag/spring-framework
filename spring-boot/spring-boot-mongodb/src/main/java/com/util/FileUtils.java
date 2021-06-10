package com.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class FileException extends RuntimeException {

	private static final long serialVersionUID = -791011360967376587L;

	public FileException(String message) {
		super(message);
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}
}

public class FileUtils {
	
	public static Path validateFile(Path path) {
		if (PathUtils.isNotExist(path)) {
			throw new FileException("Path does't exists!");
		}
		return path;
	}
	
	/**
	 * Read file to bytes array
	 */
	public static byte[] toByteArray(Path path) {
		validateFile(path);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Read a file into a String
	 */
	public static String readFile(Path path) {
		if (path != null) {
			byte[] bytes = toByteArray(path);
			if (bytes != null) {
				return new String(bytes, StandardCharsets.UTF_8);
			}
		}
		return "";
	}

}
