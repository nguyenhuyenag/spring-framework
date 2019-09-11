package com.spring.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	void init(Path location);

	Path load(Path location, String filename);

	Path load(String location, String filename);

	List<String> loadAllFile(Path location);

	void store(Path location, MultipartFile file);

	void storeAll(Path location, MultipartFile[] fileList);

	boolean fileExist(String path);

	String readFileToString(String path) throws IOException;

	String readFileToStringByCommonsIO() throws IOException;

	List<String> readFile();

	List<String> readFileByLine();

	List<String> readFileByCommonsIO(); // commons-io => POM

	List<File> listFiles(String pathname);

	List<File> listFiles(String pathname, String postfix);

}
