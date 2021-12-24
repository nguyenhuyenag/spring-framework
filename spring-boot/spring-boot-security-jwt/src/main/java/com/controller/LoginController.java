package com.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.request.LoginRequest;

@Controller
@RequestMapping("auth")
public class LoginController {

	@Autowired
	HttpServletRequest req;

	public String url() {
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
	}

	@PostMapping("login-handle")
	private ResponseEntity<String> login(@RequestBody LoginRequest login) throws ClientProtocolException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpPost httpPost = new HttpPost(url() + "/auth/login");
		Map<String, String> map = new HashMap<>();
		map.put("username", login.getUsername());
		map.put("password", login.getPassword());
		StringEntity entity = new StringEntity(mapper.writeValueAsString(map));
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try (CloseableHttpClient client = HttpClients.createDefault();) {
			HttpResponse response = client.execute(httpPost);
			System.out.println("Status:" + response.getStatusLine().toString());
			String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			return new ResponseEntity<String>(content, HttpStatus.OK);
		}
	}

}
