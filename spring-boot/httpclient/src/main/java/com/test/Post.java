package com.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Post {

	public static void postParams() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://localhost:8080/post-params");
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("username", "huyennv"));
		params.add(new BasicNameValuePair("password", "123456"));
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		try (CloseableHttpClient client = HttpClients.createDefault();) {
			HttpResponse response = client.execute(httpPost);
			System.out.println("Status:" + response.getStatusLine().toString());
			String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			System.out.println(content);
		}
	}

	public static void postJson() throws ClientProtocolException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpPost httpPost = new HttpPost("http://localhost:8080/post-json");
		Map<String, String> map = new HashMap<>();
		map.put("username", "huyennv");
		map.put("password", "123456");
		StringEntity entity = new StringEntity(mapper.writeValueAsString(map));
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try (CloseableHttpClient client = HttpClients.createDefault();) {
			HttpResponse response = client.execute(httpPost);
			System.out.println("Status:" + response.getStatusLine().toString());
			String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			System.out.println(content);
		}
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// postParams();
		postJson();
	}

}
