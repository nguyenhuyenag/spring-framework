package com.boot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
// @RequestMapping("api")
public class RequestInfo {
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@GetMapping("request")
	public Map<String, String> req(HttpServletRequest req) {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("Method", req.getMethod());
		map.put("Protocol", req.getScheme());
		map.put("Context Path", req.getContextPath());
		map.put("Server Name", req.getServerName());
		map.put("Server Port", String.valueOf(req.getServerPort()));
		map.put("Servlet Path", req.getServletPath());
		map.put("Base URL", get1BaseUrl());
		map.put("Request URL", req.getRequestURL().toString());
		String clientIp = request.getHeader("X-Real-IP");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
		map.put("Request IP", clientIp);
		// map.put("Local Port", String.valueOf(req.getLocalPort()));
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

	// private String getURLBase(HttpServletRequest req) {
	// try {
	// URL requestURL = new URL(req.getRequestURL().toString());
	// String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
	// return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// return "";
	// }

	@GetMapping("endpoints")
	public ResponseEntity<?> getEndPointsInView() {
		List<Map<String, String>> result = new ArrayList<>();
		requestMappingHandlerMapping.getHandlerMethods().forEach((k, v) -> {
			Map<String, String> map = new LinkedHashMap<>();
			// System.out.println("k=" + k.getPatternsCondition() + ", v = " + v);
			map.put("patterns", k.getPatternsCondition().toString());
			map.put("methods", k.getMethodsCondition().toString());
			map.put("todo", v.toString());
			result.add(map);
		});
		return ResponseEntity.ok(result);
	}

}
