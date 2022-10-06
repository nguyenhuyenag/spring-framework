package com.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		// System.out.println("RealIP: " + request.getHeader("X-Real-IP"));
		if (xfHeader == null) {
			// System.out.println("Host: " + request.getRemoteHost());
			//  if (ip.equals("0:0:0:0:0:0:0:1")) -> ip = "127.0.0.1";
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

}
