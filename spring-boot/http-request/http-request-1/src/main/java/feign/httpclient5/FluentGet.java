package feign.httpclient5;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

public class FluentGet {

	public static void main(String[] args) {
		get();
	}

	public static void get() {
		String url = "http://httpbin.org/get";
		try {
			Response response = Request.get(url).execute();
			System.out.println("Status: " + response.returnResponse().getCode());
			String result = response.returnContent().asString();
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
