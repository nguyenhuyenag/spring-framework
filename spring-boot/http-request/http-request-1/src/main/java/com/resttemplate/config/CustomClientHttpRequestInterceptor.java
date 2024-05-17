//package com.resttemplate.config;
//
//import java.io.IOException;
//
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//
//public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
//
//	@Override
//	public ClientHttpResponse intercept(HttpRequest request, byte[] body, //
//			ClientHttpRequestExecution execution) throws IOException {
//		logRequestDetails(request);
//		return execution.execute(request, body);
//	}
//
//	private void logRequestDetails(HttpRequest request) {
//		System.out.println("Headers: " + request.getHeaders());
//		System.out.println("Request Method: " + request.getMethod());
//		System.out.println("Request URI: " + request.getURI());
//	}
//}
