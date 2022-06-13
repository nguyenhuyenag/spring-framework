package com.resttemplate;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Jokes;
import com.pojo.LoginRequest;
import com.pojo.LoginResponse;

public class RestTest {

	private static RestTemplate restTemplate = new RestTemplate();

	private static String GET_ONE = "http://localhost:8080/api/get-one";
	private static String LOGIN = "http://localhost:8080/auth/login";

	public static void getPlainJSON() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = restTemplate.getForEntity(GET_ONE, String.class);
		if (response != null) {
			System.out.println("Status: " + response.getStatusCode().value());
			System.out.println("Body: " + response.getBody());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response.getBody());
			JsonNode joke = root.path("joke");
			System.out.println(joke);
		}
	}

	public static void getPOJO() throws JsonMappingException, JsonProcessingException {
		Jokes jokes = restTemplate.getForObject(GET_ONE, Jokes.class);
		if (jokes != null) {
			System.out.println(jokes.toString());
		}
	}

	public static void postForObject() throws JsonMappingException, JsonProcessingException {
		HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("huyennv", "123456"));
		LoginResponse login = restTemplate.postForObject(LOGIN, request, LoginResponse.class);
		if (login != null) {
			System.out.println(login);
		}
	}

	public static void exchange() throws JsonMappingException, JsonProcessingException {
		HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("huyennv", "123456"));
		ResponseEntity<LoginResponse> response = restTemplate.exchange(LOGIN, HttpMethod.POST, request,
				LoginResponse.class);
		if (response != null) {
			System.out.println(response.getStatusCode());
			LoginResponse login = response.getBody();
			System.out.println(login);
		}
	}

	public static void optionsForAllow() throws JsonMappingException, JsonProcessingException {
		Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(LOGIN);
		optionsForAllow.forEach(t -> System.out.println(t.name()));
	}

	public static void configureTimeout() {
		restTemplate = new RestTemplate(getClientHttpRequestFactory());
	}

	private static ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 5000;
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
	}

	ClientHttpRequestFactory getClientHttpRequestFactory2() {
		int timeout = 5000;
		RequestConfig config = RequestConfig.custom() //
				.setConnectTimeout(timeout) //
				.setConnectionRequestTimeout(timeout) //
				.setSocketTimeout(timeout) //
				.build();
		try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();) {
			return new HttpComponentsClientHttpRequestFactory(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// getPlainJSON();
		// getPOJO();
		// postForObject();
		// exchange();
		optionsForAllow();
		System.out.println();
	}

}
