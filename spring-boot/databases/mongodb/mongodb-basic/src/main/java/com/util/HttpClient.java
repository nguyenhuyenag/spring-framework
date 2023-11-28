package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.Document;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClient {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private HttpClient() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

//	private static <T> T readValue(InputStream is, Class<T> type) {
//		if (Objects.nonNull(is)) {
//			try {
//				return MAPPER.readValue(is, type);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

//	public static <T> T doGet(String url, Class<T> type) {
//		try {
//			// URIBuilder builder = new URIBuilder(url);
//			HttpGet httpGet = new HttpGet(url);
//			try (CloseableHttpClient client = HttpClients.createDefault();) {
//				HttpResponse response = client.execute(httpGet);
//				// LOG.info("Status: {}", response.getStatusLine().toString());
//				// String result = EntityUtils.toString(entity);
//				try (InputStream is = response.getEntity().getContent();) {
//					return readValue(is, type);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	public static Document doGet(final String url) throws HttpErrorException {
//		try (CloseableHttpClient client = HttpClients.createDefault()) {
//			HttpResponse response = client.execute(new HttpGet(url));
//			int statusCode = response.getStatusLine().getStatusCode();
//
//			if (statusCode >= 200 && statusCode < 300) {
//				HttpEntity entity = response.getEntity();
//				return (entity != null) ? readValue(entity.getContent(), Document.class) : null;
//			} else {
//				throw new HttpErrorException("HTTP request failed with status code: " + statusCode, null);
//			}
//		} catch (IOException e) {
//			throw new HttpErrorException("Failed to execute HTTP request", e);
//		}
//	}

//	public static Document doGet(final String url) {
//		try (CloseableHttpClient client = HttpClients.createDefault()) {
//			HttpResponse response = client.execute(new HttpGet(url));
//			int statusCode = response.getStatusLine().getStatusCode();
//			if (statusCode >= 200 && statusCode < 300) {
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					// Read the content first
//					InputStream content = entity.getContent();
//					Document document = readValue(content, Document.class);
//					// Consume the entity content, which also closes the InputStream
//					EntityUtils.consume(entity);
//					return document;
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public static Document doGet(String url) throws IOException {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpResponse response = client.execute(new HttpGet(url));
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try (InputStream content = entity.getContent()) {
						return MAPPER.readValue(content, Document.class);
					} finally {
						EntityUtils.consume(entity);
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		String url = "https://randomuser.me/api/";
		Document document = doGet(url);
		System.out.println(document.toJson());
		ArrayList<?> arrayList = document.get("results", ArrayList.class);
		System.out.println(arrayList);
		System.out.println(arrayList.get(0));
	}

}
