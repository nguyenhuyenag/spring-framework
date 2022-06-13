package com.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private static String GET_ONE_URL = "http://localhost:8080/api/get-one";
	private static String LOGIN_URL = "http://localhost:8080/api/get-one";

	public static void getPlainJSON() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = restTemplate.getForEntity(GET_ONE_URL, String.class);
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
		Jokes jokes = restTemplate.getForObject(GET_ONE_URL, Jokes.class);
		if (jokes != null) {
			System.out.println(jokes.toString());
		}
	}

	public static void postForObject() throws JsonMappingException, JsonProcessingException {
		HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("huyennv", "123456"));
		LoginResponse login = restTemplate.postForObject(LOGIN_URL, request, LoginResponse.class);
		if (login != null) {
			System.out.println(login);
		}
	}
	
	public static void exchange() throws JsonMappingException, JsonProcessingException {
		HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("huyennv", "123456"));
		ResponseEntity<LoginResponse> response = restTemplate.exchange(LOGIN_URL, HttpMethod.POST, request, LoginResponse.class);
		if (response != null) {
			System.out.println(response.getStatusCode());
			LoginResponse login = response.getBody();
			System.out.println(login);
		}
	}
	
	public static void submitFormData() throws JsonMappingException, JsonProcessingException {
		String url = "http://localhost:8080/auth/login";
		HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("huyennv", "123456"));
		// LoginResponse login = restTemplate.postForObject(url, request, LoginResponse.class);
		ResponseEntity<LoginResponse> response = restTemplate.exchange(url, HttpMethod.POST, request, LoginResponse.class);
		if (response != null) {
			System.out.println(response.getStatusCode() + " : " +  HttpStatus.OK);
			LoginResponse login = response.getBody();
			System.out.println(login);
		}
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// getPlainJSON();
		// getPOJO();
		// postForObject();
		exchange();
	}

}
