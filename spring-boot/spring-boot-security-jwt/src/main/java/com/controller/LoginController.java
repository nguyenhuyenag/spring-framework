package com.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.reponse.ErrorResponse;
import com.request.LoginRequest;
import com.util.JsonUtils;

@Controller
@RequestMapping("auth")
public class LoginController {

	@Autowired
	HttpServletRequest req;

	public String url() {
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
	}

	@PostMapping("login-handle")
	private ResponseEntity<?> login(@RequestBody(required = false) LoginRequest login, HttpServletRequest req)
			throws ClientProtocolException, IOException {
		if (login == null) {
			ErrorResponse error = new ErrorResponse();
			error.setError("Required request body is missing");
			error.setMessage("");
			error.setPath(req.getRequestURI());
			return ResponseEntity.ok(error);
		}
		HttpPost httpPost = new HttpPost(url() + "/auth/login");
		StringEntity entity = new StringEntity(JsonUtils.toJSON(login));
		httpPost.setEntity(entity);
		// httpPost.setHeader("Accept", "application/json");
		// httpPost.setHeader("Content-type", "application/json");
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpResponse response = client.execute(httpPost);
			System.out.println("Status: " + response.getStatusLine());
			// IOUtils.toString(response.getEntity().getContent());
			String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			if (content.contains("token")) {
				JsonNode jsonNode = JsonUtils.toJsonNode(content);
				return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
			}
			return new ResponseEntity<String>(content, HttpStatus.OK);
		}
	}

}
