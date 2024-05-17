package feign.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientExample {

	public static void main(String[] args) throws IOException {
		String token = AuthenticationClient.TOKEN;
		retrieveProfile(token);
	}

	/*-
		[GET] https://api.escuelajs.co/api/v1/auth/profile 
		# Headers 
		{
			"Authorization": "Bearer {your access token}" 
		}
	 */
	public static void retrieveProfile(String token) throws IOException {
		final OkHttpClient client = new OkHttpClient();
		final String API_URL = "https://api.escuelajs.co/api/v1/auth/profile";
		Request request = new Request.Builder() //
				.url(API_URL) //
				.header("Authorization", "Bearer " + token)//
				.build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			System.out.println(result);
		}
	}

}
