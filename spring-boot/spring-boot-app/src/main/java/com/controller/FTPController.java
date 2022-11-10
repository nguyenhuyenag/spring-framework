package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FileStore;
import com.entity.MultiFile;
import com.entity.MyFile;
import com.service.FileStoreService;
import com.util.Base64Utils;
import com.util.MediaTypeUtils;

@Controller
@RequestMapping("ftp")
public class FTPController {

	private static final String DEFAULT_ID = "J9VWJBPIJKQCMFY4F8UM";

	@Autowired
	FileStoreService fileStoreService;

	@GetMapping("upload")
	public String upload() {
		return "upload";
	}

	@PostMapping("upload")
	public String upload(MyFile myFile) {
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			String fileName = multipartFile.getOriginalFilename();
			FileStore fileStore = new FileStore();
			fileStore.setFileName(fileName);
			String content = Base64Utils.encodeToString(multipartFile.getBytes());
			fileStore.setFileContent(content);
			fileStoreService.save(fileStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload";
	}

	@GetMapping("multi-upload")
	public String multiUpload() {
		return "multi-upload";
	}

	@PostMapping("multi-upload")
	public String multiUpload(MultiFile myFile) {
		try {
			MultipartFile[] multipartFiles = myFile.getMultipartFile();
			for (MultipartFile multipartFile : multipartFiles) {
				String fileName = multipartFile.getOriginalFilename();
				FileStore fileStore = new FileStore();
				fileStore.setFileName(fileName);
				String content = Base64Utils.encodeToString(multipartFile.getBytes());
				fileStore.setFileContent(content);
				fileStoreService.save(fileStore);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "multi-upload";
	}

	@GetMapping("download")
	public String downloadView(Model model) {
		List<FileStore> files = fileStoreService.findAll();
		model.addAttribute("files", files);
		return "download";
	}

	@GetMapping("download-file")
	public ResponseEntity<?> download(@RequestParam(defaultValue = DEFAULT_ID) String fileid) {
		FileStore file = fileStoreService.findByFileId(fileid);
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(file.getFileName());
		// System.out.println("mediaType: " + mediaType);
		// System.out.println("fileName: " + file.getFileName());
		String fileContent = file.getFileContent();
		byte[] data = Base64Utils.decodeToByte(fileContent);
		return ResponseEntity.ok() //
				.contentType(mediaType) //
				.contentLength(data.length) //
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
				.body(new ByteArrayResource(data));
	}

	@GetMapping("download-from-url")
	public String downloadFromUrlView() {
		return "download-from-url";
	}

	private static void showAllHeaderFields(String downloadUrl) throws IOException {
		URL url = URI.create(downloadUrl).toURL();
		URLConnection conn = url.openConnection();
		// get all headers
		Map<String, List<String>> map = conn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		// get and verify the header field
		String fieldValue = conn.getHeaderField("Content-Disposition");
		if (fieldValue == null || !fieldValue.contains("filename=")) {
			// no file name there -> throw exception ...
		}
		// parse the file name from the header field
		String filename = fieldValue.substring(fieldValue.indexOf("filename=") + 9, fieldValue.length());
		System.out.println(filename);
		// create file in systems temporary directory
		// File download = new File(System.getProperty("java.io.tmpdir"), filename);
	}

	private static byte[] getBytes(String downloadUrl) throws IOException {
		showAllHeaderFields(downloadUrl);
		URL url = URI.create(downloadUrl).toURL();
		try (InputStream is = url.openStream()) {
			return IOUtils.toByteArray(is);
			// return Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	@PostMapping("download-from-url")
	public ResponseEntity<?> downloadFromUrl(String url) throws IOException {
		// Path path = Paths.get("file/download-file");
		byte[] data = getBytes(url);
		InputStream is = new ByteArrayInputStream(data);
		String mimeType = URLConnection.guessContentTypeFromStream(is);
		System.out.println("MimeType: " + mimeType);
		// System.out.println(URLConnection.getFileNameMap().getContentTypeFor(fileName));
		MediaType mediaType = MediaTypeUtils.getMediaTypeFromMineType(mimeType);
		// URL url2 = URI.create(url).toURL();
		// URLConnection conn = url2.openConnection();
		// System.out.println("ContentType: " + );
		// FileStore file = fileStoreService.findByFileId("");
		// MimeTypeMap.getSingleton().getExtensionFromMimeType(conn.getContentType());
		// System.out.println("mediaType: " + mediaType);
		// System.out.println("fileName: " + file.getFileName());
		// String fileContent = file.getFileContent();
		// byte[] data = Base64Utils.decodeToByte(null);
		return ResponseEntity.ok() //
				.contentType(mediaType) //
				.contentLength(data.length) //
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=abc") //
				.body(new ByteArrayResource(data));
	}

}
