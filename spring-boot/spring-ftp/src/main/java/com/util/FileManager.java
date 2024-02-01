package com.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

public class FileManager {
	
	// ???
	public static String getFileName(URL url) throws IOException {
		URLConnection conn = url.openConnection();
		String fieldValue = conn.getHeaderField("Content-Disposition");
		if (fieldValue == null || !fieldValue.contains("filename=")) {
			return "";
		}
		return fieldValue.substring(fieldValue.indexOf("filename=") + 9);
	}
	
	// Xem thong tin cua file download
	protected static void showAllHeaderFields(String downloadUrl) throws IOException {
		URLConnection conn = URI.create(downloadUrl).toURL().openConnection();
		// get all headers
		Map<String, List<String>> map = conn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		// get and verify the header field
		String fieldValue = conn.getHeaderField("Content-Disposition");
		if (fieldValue != null && fieldValue.contains("filename=")) {
			// parse the file name from the header field
			String filename = fieldValue.substring(fieldValue.indexOf("filename=") + 9);
			System.out.println("FileName: " + filename);
		}
	}
	
	// ???
	protected static long downloadFile(String downloadUrl) throws IOException {
		// showAllHeaderFields(downloadUrl);
		URL url = URI.create(downloadUrl).toURL();
		try (InputStream is = url.openStream()) {
			byte[] byteArray = IOUtils.toByteArray(is);
			InputStream input = new ByteArrayInputStream(byteArray);
			String mimeType = URLConnection.guessContentTypeFromStream(input);
			MediaType mediaType = MediaTypeUtils.fromMineType(mimeType);
			System.out.println("MimeType: " + mimeType);
			System.out.println("Type: " + mediaType.getType());
			System.out.println("Subtype: " + mediaType.getSubtype());
			String fileName = getFileName(url);
			Path path = Paths.get("download", fileName);
			// Files.write(path, byteArray);
			return Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	public static void main(String[] args) throws IOException {
		String link = "http://localhost:8080/ftp/download-ajax/?fileId=21ff6b0a-7005-4fae-b510-b52a0edaf9b7";
		// showAllHeaderFields(link);
		downloadFile(link);
	}

}
