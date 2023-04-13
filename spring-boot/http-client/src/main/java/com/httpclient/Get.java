package com.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pojo.Response;
import com.util.JsonUtils;

public class Get {

	private static final Logger LOG = LoggerFactory.getLogger(Get.class);

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

	// completed method
	public static <T> T doGet(String url, Class<T> type) {
		try {
			URIBuilder builder = new URIBuilder(url);
			HttpGet httpGet = new HttpGet(builder.build());
			HttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(httpGet);
			LOG.info("Status: {}", response.getStatusLine().toString());
			InputStream is = response.getEntity().getContent();
			return JsonUtils.readValue(is, type);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// callSlowService();
		String url = "https://mocki.io/v1/d943e462-f4f3-4e3f-a5a2-56d8ef3d8657";
		Response res = doGet(url, Response.class);
		System.out.println(res.getRply().getName());
		System.out.println();
	}

}
