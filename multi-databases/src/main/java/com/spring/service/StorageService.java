package com.spring.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	List<String> loadAllFile();

	Resource loadAsResource(String filename);

	void deleteAll();

	boolean deleteFileByName(String filename) throws MalformedURLException;

	boolean acceptFile(String filename);
}
