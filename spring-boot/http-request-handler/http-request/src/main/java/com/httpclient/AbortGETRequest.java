package com.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class AbortGETRequest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient instance = HttpClients.custom().build();
		final HttpGet request = new HttpGet("SAMPLE_URL");
		HttpResponse response = instance.execute(request);

		final HttpEntity entity = response.getEntity();

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (entity != null) {
			System.out.println("Response content length: " + entity.getContentLength());
		}
		System.out.println("----------------------------------------");

		// Do not feel like reading the response body
		// Call abort on the request object
		request.abort();
	}

}
