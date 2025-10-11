package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SendRequestController {

	public String info(HttpServletRequest req) {
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
	}

	@GetMapping("send-request")
	public String request(HttpServletRequest request, Model model) {
		// Map<String, String[]> map = req.getParameterMap();
		// map.forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));

		System.out.println("RequestURI: " + request.getRequestURI());
		System.out.println("RequestURL: " + request.getRequestURL());
		System.out.println("QueryString: " + request.getQueryString());
		System.out.println("ServerPort: " + request.getServerPort());
		System.out.println("ServletPath: " + request.getServletPath());
		System.out.println("Info: " + info(request));
		System.out.println("Locale: " + request.getLocale());
		System.out.println("AuthType: " + request.getAuthType());
		System.out.println("CharacterEncoding: " + request.getCharacterEncoding());
		System.out.println("ContentType: " + request.getContentType());
		System.out.println("ContextPath: " + request.getContextPath());
		System.out.println("LocalAddr: " + request.getLocalAddr());
		System.out.println("LocalName: " + request.getLocalName());
		System.out.println("LocalPort: " + request.getLocalPort());
		System.out.println("Method: " + request.getMethod());
		System.out.println("PathInfo: " + request.getPathInfo());
		System.out.println("ServerName: " + request.getServerName());

		// Pattern will be either: '/search/{subpath}/other' or '/find/other/{subpath}'
		// String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		// String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		// AntPathMatcher apm = new AntPathMatcher();
		// String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
		// System.out.println("Pattern matched 1: " + path);
		// System.out.println("Pattern matched 2: " + bestMatchPattern);
		// System.out.println("Pattern final: " + finalPath);
		// model.addAttribute("controller_path", bestMatchPattern);

		// (2) From Controller
		Map<String, String> requestInfo = new HashMap<>();
		requestInfo.put("contextPath", request.getContextPath());
		requestInfo.put("localAddr", request.getLocalAddr());

		String scheme = request.getHeader("X-Forwarded-Proto"); // Kiểm tra header
		if (scheme == null) {
			scheme = request.getScheme(); // Nếu không có, dùng scheme mặc định
		}
		requestInfo.put("scheme", scheme);

		// Tự động phát hiện HTTP/HTTPS
		requestInfo.put("baseUrl", ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());

		// model.addAttribute("requestInfo", requestInfo);
		request.setAttribute("requestInfo", requestInfo);

		return "send-request";
	}

}
