package com.util;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MediaTypeUtils {

	private static ServletContext servletContext;

	public MediaTypeUtils(ServletContext context) {
		MediaTypeUtils.servletContext = context;
	}

	/*
		 Input: abc.pdf, abc.xml, ...
		 Output: application/pdf, application/xml, image/gif, ...
	 */
	public static MediaType fromFileName(String fileName) {
		if (servletContext != null) {
			String mineType = servletContext.getMimeType(fileName);
			if (mineType != null) {
				return MediaType.parseMediaType(mineType);
			}
		}
		// Loại generic, không rõ định dạng cụ thể của dữ liệu
		return MediaType.APPLICATION_OCTET_STREAM;
	}

	public static MediaType fromMineType(String mineType) {
		if (mineType != null) {
			return MediaType.parseMediaType(mineType);
		}
		return MediaType.APPLICATION_OCTET_STREAM;
	}

}
