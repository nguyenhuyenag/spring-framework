package com.okhttp;

import java.io.IOException;
import java.util.Map;

import com.util.JsonUtils;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * https://fakeapi.platzi.com/en/rest/auth-jwt
 */
public class AuthenticationClient {

	public static final String AUTH_URL = "https://api.escuelajs.co/api/v1/auth/login";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final String TOKEN = getToken();

	/**
	 * Get Json Web Token (JWT)
	 */
	private static String getToken() {
		RequestBody requestBody = buildBody2();

		Request request = new Request.Builder() //
				.url(AUTH_URL) //
				.post(requestBody) //
				.build();

		OkHttpClient client = new OkHttpClient();
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code: " + response);
			}
			String token = response.body().string();
			Map<String, String> body = JsonUtils.asMap(token);
			// {"access_token": "...", "refresh_token": "..."}
			return body.get("access_token");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static RequestBody buildBody1() {
		String json = "{ \"email\": \"john@mail.com\", \"password\": \"changeme\" }";
		return RequestBody.create(JSON, json);
	}
	
	public static RequestBody buildBody2() {
		return new FormBody.Builder() //
				.add("email", "john@mail.com") //
				.add("password", "changeme") //
				.build();
	}

}
