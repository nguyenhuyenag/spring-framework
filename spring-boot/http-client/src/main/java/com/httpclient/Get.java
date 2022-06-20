package com.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

public class Get {

	public static void test() throws UnsupportedOperationException, IOException, URISyntaxException {
		URIBuilder builder = new URIBuilder("http://localhost:8080/get");
		builder.addParameter("author", "huyennv");
		HttpGet httpGet = new HttpGet(builder.build());
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(httpGet);
		System.out.println("Status:" + response.getStatusLine().getStatusCode());
		String result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
		System.out.println(result);
	}

	public static void callSlowService() {
		try {
			final RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5 * 1000).build();
			URIBuilder builder = new URIBuilder("http://localhost:8080/v1/slow-service?delay=20");
			HttpGet httpGet = new HttpGet(builder.build());
			httpGet.setConfig(requestConfig);
			HttpClient httpClient = HttpClients.createDefault();
			HttpResponse response = httpClient.execute(httpGet);
			System.out.println("Status: " + response.getStatusLine().getStatusCode());
			String result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			System.out.println(result);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		callSlowService();
		System.out.println();
	}

}
