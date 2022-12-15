package com.boot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileToFile {

	public File convertToFile(MultipartFile multipartFile) throws FileNotFoundException, IOException {
		File file = new File("src/main/resources/targetFile.tmp");
		try (OutputStream os = new FileOutputStream(file)) {
			os.write(multipartFile.getBytes());
		}
		return file;
	}

	public File convertToFile2(MultipartFile multipartFile) throws FileNotFoundException, IOException {
		InputStream initialStream = multipartFile.getInputStream();
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);
		File targetFile = new File("src/main/resources/targetFile.tmp");
		try (OutputStream outStream = new FileOutputStream(targetFile)) {
			outStream.write(buffer);
		}
		return targetFile;
	}

	public File convertToFile3(MultipartFile multipartFile) throws IllegalStateException, IOException {
		File file = new File("src/main/resources/targetFile.tmp");
		multipartFile.transferTo(file);
		return file;
	}

}
