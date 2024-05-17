package com.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class ConnectionPool {

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {

		PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
		pool.setDefaultMaxPerRoute(1);
		pool.setMaxTotal(1);
		final CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(pool).build();

		final String url = "https://www.google.com/";
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					HttpGet httpGet = new HttpGet(url);
					try (CloseableHttpResponse response = httpclient.execute(httpGet);) {
						HttpEntity entity = response.getEntity();
						EntityUtils.consume(entity);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		Thread.sleep(100000);
	}

}
