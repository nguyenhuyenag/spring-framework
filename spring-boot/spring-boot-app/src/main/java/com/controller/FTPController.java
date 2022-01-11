package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.Base64Utils;
import com.util.MediaTypeUtils;

@RestController
@RequestMapping("fpt")
public class FTPController {

	private static final String DEFAULT_ID = "B5LUDZCNLSJ0VLYDAES0";

	@Autowired
	FileStoreService fileStoreService;

	// @Autowired
	// private ServletContext servletContext;

	@GetMapping("download")
	public ResponseEntity<?> download(@RequestParam(defaultValue = DEFAULT_ID) String fileid) {
		FileStore file = fileStoreService.findById(fileid);
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(file.getFileName());
		// System.out.println("mediaType: " + mediaType);
		// System.out.println("fileName: " + file.getFileName());
		String fileContent = file.getFileContent();
		byte[] data = Base64Utils.decodeToByte(fileContent);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok() //
				.contentType(mediaType) //
				.contentLength(data.length) //
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
				.body(resource);
	}

}
