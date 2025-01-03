package com.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static String getClientIPAddress(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

//	public static String getClientIP(HttpServletRequest request) {
//		String xfHeader = request.getHeader("X-Forwarded-For");
//		// System.out.println("RealIP: " + request.getHeader("X-Real-IP"));
//		if (xfHeader == null) {
//			// System.out.println("Host: " + request.getRemoteHost());
//			//  if (ip.equals("0:0:0:0:0:0:0:1")) -> ip = "127.0.0.1";
//			return request.getRemoteAddr();
//		}
//		return xfHeader.split(",")[0];
//	}

}
