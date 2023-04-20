package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.exception.FileException;
import com.exception.FileNotFoundException;
import com.service.FileService;
import com.service.StorageService;
import com.util.PathUtils;
import com.util.PropertiesReader;

@Service
public class StorageServiceImpl implements StorageService {

	private Path location;

	public StorageServiceImpl() {
		this.location = Paths.get(PathUtils.getPathUpload());
	}

	@Autowired
	private FileService fileService;

	@Override
	public void init() {
		fileService.init(location);
	}

	@Override
	public void store(MultipartFile file) {
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
	public Resource loadAsResource(String filename) {
		try {
			Path file = fileService.load(location, filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(location.toFile());
	}

	@Override
	public boolean deleteFileByName(String filename) throws MalformedURLException {
		Path file = fileService.load(location, filename);
		return FileSystemUtils.deleteRecursively(file.toFile());
	}

	@Override
	public List<String> loadAllFile() {
		return fileService.loadAllFile(location);
	}

	@Override
	public boolean acceptFile(String filename) {
		List<String> acceptList = PropertiesReader.FILE_ACCEPT;
		for (String accept : acceptList) {
			if (!filename.toUpperCase().endsWith(accept.toUpperCase())) {
				return false;
			}
		}
		return true;
	}

}
