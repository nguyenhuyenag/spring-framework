package com.util;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

// @Component
public class MediaTypeUtils {

	private static ServletContext servletContext;

	public MediaTypeUtils(ServletContext context) {
		MediaTypeUtils.servletContext = context;
	}

	// input: abc.zip, abc.pdf,..
	// output: application/pdf, application/xml, image/gif, ...
	public static MediaType getMediaTypeForFileName(String fileName) {
		try {
			if (servletContext != null) {
				String mineType = servletContext.getMimeType(fileName);
				return MediaType.parseMediaType(mineType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MediaType.APPLICATION_OCTET_STREAM;
	}

}
