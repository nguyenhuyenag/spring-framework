package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.FileStoreRepository;
import com.service.FileStoreService;
import com.service.Service;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	Service service;
	
	@Autowired
	FileStoreService fileStoreService;
	
	@Autowired
	FileStoreRepository fileStoreRepository;

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
		// Path path = Paths.get("C:\\Users\\huyennv\\Desktop\\ordinal-numbers-english.png");
		// String s = Base64Utils.encodeToString(path);
		// FileStore file = fileStoreService.findById("B5LUDZCNLSJ0VLYDAES0");
		// file.setFileContent(s.trim());
		// fileStoreRepository.save(file);
	}

}
