package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Post {

	private static final Logger LOG = LoggerFactory.getLogger(Post.class);

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
		HttpPost httpPost = new HttpPost("http://localhost:8082/auth/login");
		Map<String, String> map = new HashMap<>();
		map.put("email", "lam.ln@ts24corp.com");
		map.put("password", "123456");
		ObjectMapper mapper = new ObjectMapper();
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

	public static void postJson2() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://localhost:8082/auth/login");

		String json = "{\"email\": \"lam.ln@ts24corp.com\", \"password\": \"123456\"}";
		StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		HttpResponse response = httpClient.execute(httpPost);
		String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
		System.out.println(content);
	}

	public static <T> T doPost(Object data, String url, Class<T> type) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(JsonUtils.toJSON(data));
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(httpPost);
			LOG.info("Status: {}", response.getStatusLine().toString());
			InputStream is = response.getEntity().getContent();
			return JsonUtils.readValue(is, type);
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String doPost(Object data, String url) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(JsonUtils.toJSON(data));
			httpPost.setEntity(entity);
			HttpResponse response = client.execute(httpPost);
			LOG.info("Status = {}", response.getStatusLine().getStatusCode());
			return EntityUtils.toString(response.getEntity());
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String setTimeout(Object data, String url) {
//			int timeout = 5000;
//		    RequestConfig config = RequestConfig.custom()
//		      .setConnectTimeout(timeout)
//		      .setConnectionRequestTimeout(timeout)
//		      .setSocketTimeout(timeout)
//		      .build();
		final  RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
		// try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			StringEntity entity = new StringEntity(JsonUtils.toJSON(data));
			httpPost.setEntity(entity);
			final HttpResponse response = httpClient.execute(httpPost);
			LOG.info("Status = {}", response.getStatusLine().getStatusCode());
			return EntityUtils.toString(response.getEntity());
		} catch (UnsupportedOperationException | IOException e) {
			// e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// postParams();
		// postJson();
		// postJson2();
		String URL = "http://localhost:8080/auth/login";
		Map<String, String> login = new HashMap<>();
		login.put("username", "huyennv");
		login.put("password", "123456");
		System.out.println(JsonUtils.toJSON(login));
		String s = setTimeout(login, URL);
		System.out.println(s);
	}

}
