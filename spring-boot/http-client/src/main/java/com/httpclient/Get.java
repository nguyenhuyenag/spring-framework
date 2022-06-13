package com.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

public class Get {

	public static void main(String[] args) throws UnsupportedOperationException, IOException, URISyntaxException {
		URIBuilder builder = new URIBuilder("http://localhost:8080/get");
		builder.addParameter("author", "huyennv");
		HttpGet httpGet = new HttpGet(builder.build());
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(httpGet);
		System.out.println("Status:" + response.getStatusLine().getStatusCode());
		String result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
		System.out.println(result);
	}

}
