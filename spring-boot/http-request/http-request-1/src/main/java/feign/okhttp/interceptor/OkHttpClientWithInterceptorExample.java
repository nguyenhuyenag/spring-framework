package feign.okhttp.interceptor;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientWithInterceptorExample {
	
	private static OkHttpClient client;
    
    public static void main(String[] args) throws IOException {
        client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new AuthInterceptor())
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();
        profile();
    }
    
	public static void profile() throws IOException {
		final String API_URL = "https://api.escuelajs.co/api/v1/auth/profile";
		Request request = new Request.Builder() //
				.url(API_URL) //
				.build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			System.out.println(result);
		}
	}
	
}
