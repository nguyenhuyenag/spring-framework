package com.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.exception.FileException;
import com.service.FileService;
import com.util.PathUtils;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public void init(Path location) {
		try {
			Files.createDirectories(location);
		} catch (IOException e) {
			throw new FileException("Could not initialize storage", e);
		}
	}

	@Override
	public Path load(String location, String filename) {
		return Paths.get(location).resolve(filename);
	}

	@Override
	public Path load(Path location, String filename) {
		return location.resolve(filename);
	}

	@Override
	public void store(Path location, MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new FileException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new FileException("Cannot store file with relative path outside current directory " + filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, location.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new FileException("Failed to store file " + filename, e);
		}
	}

	@Override
	public void storeAll(Path location, MultipartFile[] fileList) {
		for (MultipartFile file : fileList) {
			store(location, file);
		}
	}

	@Override
	public boolean fileExist(String path) {
		return Files.exists(Paths.get(path)); // java 8
	}

	@Override
	public String readFileToString(String path) throws IOException {
		if (fileExist(path)) {
			return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		}
		return "file not found!";
	}

	@Override
	public String readFileToStringByCommonsIO() throws IOException {
		String PATH = PathUtils.getPathResources() + "static/data.txt";
		File file = new File(PATH);
		return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	}

	@Override
	public List<String> readFile() {
		String PATH = PathUtils.getPathResources() + "static/data.txt";
		List<String> fileContent = new ArrayList<>();
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(PATH));
			fileContent = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	@Override
	public List<String> readFileByLine() {
		List<String> fileContent = new ArrayList<>();
		String PATH = PathUtils.getPathResources() + "static/data.txt";
		try {
			List<String> allLines = Files.readAllLines(Paths.get(PATH));
			for (String line : allLines) {
				fileContent.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	@Override
	public List<String> readFileByCommonsIO() {
		List<String> fileContent = new ArrayList<>();
		String PATH = PathUtils.getUserDir() + "/readme.txt";
		try {
			File f = new File(PATH);
			List<String> lines = FileUtils.readLines(f, StandardCharsets.UTF_8);
			for (String line : lines) {
				fileContent.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	@Override
	public List<String> loadAllFile(Path location) {
		return Stream.of(new File(location.toString()).listFiles())
					 .filter(file -> !file.isDirectory())
					 .map(File::getName)
					 .collect(Collectors.toList());
	}

	@Override
	public List<File> listFiles(String pathname) {
		File directory = new File(pathname);
		List<File> list = new ArrayList<File>();
		File[] listFiles = directory.listFiles(); // Get all files from a directory
		if (listFiles != null) {
			for (File file : listFiles) {
				if (file.isFile()) {
					list.add(file);
				} else if (file.isDirectory()) {
					list.addAll(listFiles(file.getAbsolutePath()));
				}
			}
		}
		return list;
	}

	@Override
	public List<File> listFiles(String pathname, String postfix) {
		File directory = new File(pathname);
		List<File> list = new ArrayList<File>();
		/* File[] listFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(postfix)); */
		File[] listFiles = directory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(postfix);
			}
		});
		if (listFiles != null) {
			for (File file : listFiles) {
				if (file.isFile()) {
					list.add(file);
				} else if (file.isDirectory()) {
					list.addAll(listFiles(file.getAbsolutePath()));
				}
			}
		}
		return list;
	}

}
