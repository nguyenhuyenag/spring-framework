package com.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.service.StorageService;

@Controller
@RequestMapping("api/files")
public class FTPController {

	private final int MAX_FILE_SIZE = 128 * 1024;

	public FTPController(StorageService _storageService) {
		_storageService.init();
	}

	@Autowired
	private StorageService storageService;

	@GetMapping("load-all")
	private ResponseEntity<List<String>> loadAllFile() throws IOException {
		List<String> list = storageService.loadAllFile();
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}

	@PostMapping("upload")
	private ResponseEntity<List<String>> handleFileUpload(@RequestParam("file") MultipartFile[] fileList) {
		String successMsg;
		List<String> listMessage = new ArrayList<String>();
		for (MultipartFile file : fileList) {
			if (file.getSize() <= MAX_FILE_SIZE) {
				storageService.store(file);
				successMsg = "You successfully uploaded " + file.getOriginalFilename();
				listMessage.add(successMsg);
			}
		}
		return new ResponseEntity<List<String>>(listMessage, HttpStatus.OK);
	}

	@GetMapping("download")
	private ResponseEntity<Resource> download(@RequestParam("filename") String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("delete")
	private ResponseEntity<Boolean> delete(@RequestParam("filename") String filename) throws MalformedURLException {
		boolean b = storageService.deleteFileByName(filename);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

}
