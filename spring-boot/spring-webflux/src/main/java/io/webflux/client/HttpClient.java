package io.webflux.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClient {

	private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private HttpClient() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	private static <T> T readValue(InputStream is, Class<T> type) {
		if (Objects.nonNull(is)) {
			try {
				return MAPPER.readValue(is, type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> T doGet(String url, Class<T> type) {
		try {
			URIBuilder builder = new URIBuilder(url);
			HttpGet httpGet = new HttpGet(builder.build());
			try (CloseableHttpClient client = HttpClients.createDefault();) {
				HttpResponse response = client.execute(httpGet);
				LOG.info("Status: {}", response.getStatusLine().toString());
				try (InputStream is = response.getEntity().getContent();) {
					return readValue(is, type);
				}
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// callSlowService();
		String url = "https://randomuser.me/api/";
		// String url = "https://mocki.io/v1/d943e462-f4f3-4e3f-a5a2-56d8ef3d8657";
		// BSONObject obj = new BSONObject();
		JSONObject res = doGet(url, JSONObject.class);
		System.out.println(res);
	}

}
