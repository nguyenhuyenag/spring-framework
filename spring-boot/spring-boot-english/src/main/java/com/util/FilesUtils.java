package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class FileException extends RuntimeException {

	private static final long serialVersionUID = -791011360967376587L;

	public FileException(String message) {
		super(message);
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}
}

public class FilesUtils {

	public static boolean createFile(Path path) {
		if (path == null) {
			System.out.println("Path `" + path + "` does't exists!");
			return false;
		}
		Path parent = path.getParent();
		if (PathUtils.isNotExist(parent)) {
			PathUtils.createDirectories(parent);
		}
		try {
			return path.toFile().createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeByteArrayToFile(Path path, byte[] bytes, boolean append) {
		if (PathUtils.isNotExist(path)) {
			FilesUtils.createFile(path);
		}
		try {
			if (!append) {
				Files.write(path, bytes);
			} else {
				Files.write(path, bytes, StandardOpenOption.APPEND);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean writeStringToFile(Path path, String content, boolean append) {
		if (content == null) {
			throw new FileException("Content is NULL!");
		}
		return writeByteArrayToFile(path, content.getBytes(), append);
	}

}
