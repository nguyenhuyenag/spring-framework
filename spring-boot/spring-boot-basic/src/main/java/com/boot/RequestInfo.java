package com.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
// @RequestMapping("api")
public class RequestInfo {

	@GetMapping("request")
	private Map<String, String> req(HttpServletRequest req) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Method", req.getMethod());
		map.put("Protocol", req.getScheme());
		map.put("Server Name", req.getServerName());
		map.put("Server Port", String.valueOf(req.getServerPort()));
		// map.put("Local Port", String.valueOf(req.getLocalPort()));
		map.put("Context Path", req.getContextPath());
		map.put("Servlet Path", req.getServletPath());
		map.put("Base URL", get1BaseUrl());
		map.put("Request URL", req.getRequestURL().toString());
		// map.put("Request URI", req.getRequestURI());
		// map.put("URL 2", get2BaseUrl(req));
		// map.put("Encoding", req.getCharacterEncoding());
		// map.put("Local Address", req.getLocalAddr());
		// map.put("Remote Address", req.getRemoteAddr());
		// map.put("Remote Host", req.getRemoteHost());
		return map;
	}

	private String get1BaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
	}

	public String get2BaseUrl(HttpServletRequest req) {
		return req.getRequestURL().toString().replace(req.getRequestURI(), req.getContextPath());
	}

	//	private String getURLBase(HttpServletRequest req) {
	//		try {
	//			URL requestURL = new URL(req.getRequestURL().toString());
	//			String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
	//			return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	//		} catch (MalformedURLException e) {
	//			e.printStackTrace();
	//		}
	//		return "";
	//	}

}
