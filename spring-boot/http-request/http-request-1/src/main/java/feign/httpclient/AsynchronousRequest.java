package feign.httpclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AsynchronousRequest {

	public static void invoke() {

		try (CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();) {
			client.start();

			final SimpleHttpRequest request = SimpleRequestBuilder.get() //
					.setUri("URL") //
					.addHeader("API_KEY_NAME", "API_KEY_VALUE") //
					.build();

			Future<SimpleHttpResponse> future = client.execute(request, new FutureCallback<SimpleHttpResponse>() {
				@Override
				public void completed(SimpleHttpResponse result) {
					String response = result.getBodyText();
					System.out.println("response::" + response);
				}

				@Override
				public void failed(Exception ex) {
					System.out.println("response::" + ex);
				}

				@Override
				public void cancelled() {
					// Auto-generated method stub
				}

			});
			
			HttpResponse response = future.get();

			// Get HttpResponse Status
			System.out.println("version " + response.getVersion()); // HTTP/1.1
			System.out.println(response.getCode()); // 200
			System.out.println(response.getReasonPhrase()); // OK

		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void invokePost() {
		StringEntity stringEntity = new StringEntity(prepareRequest());
		HttpPost httpPost = new HttpPost("https://reqbin.com/echo/post/json");

		httpPost.setEntity(stringEntity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		try (CloseableHttpClient httpClient = HttpClients.createDefault();

				CloseableHttpResponse response = httpClient.execute(httpPost);) {

			// Get HttpResponse Status
			System.out.println("version " + response.getVersion()); // HTTP/1.1
			System.out.println(response.getCode()); // 200
			System.out.println(response.getReasonPhrase()); // OK

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// return it as a String
				String result = EntityUtils.toString(entity);
				System.out.println(result);
			}
		} catch (IOException | org.apache.hc.core5.http.ParseException e) {
			e.printStackTrace();
		}

	}

	private static String prepareRequest() {
		Map<String, String> values = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("Id", "12345");
				put("Customer", "Roger Moose");
				put("Quantity", "3");
				put("Price","167.35");
			}
		};

		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody;
		try {
			requestBody = objectMapper.writeValueAsString(values);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		return requestBody;
	}

	public static void main(String[] args) {
		// invoke();
		invokePost();
	}

}
