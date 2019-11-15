package com.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.util.ResourceUtils;

public class ReadFileResource {

	static final String src = "file/data.txt";

	public static void useClassLoader() throws IOException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(src).getFile());
		Files.readAllLines(file.toPath()).forEach(System.out::println);
	}

	public static void useResourceUtils() throws IOException {
		File file = ResourceUtils.getFile("classpath:" + src);
		Files.readAllLines(file.toPath()).forEach(System.out::println);
	}

	public static void main(String[] args) throws IOException {
		useClassLoader();
		// useResourceUtils();
	}

}
