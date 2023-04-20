package com.util;

import java.util.concurrent.TimeUnit;

import org.springframework.web.client.RestTemplate;

import com.entity.Jokes;

public class RestUtils {

	private final static String URL = "http://api.icndb.com/jokes/random";

	private static final RestTemplate restTemplate = new RestTemplate();

	public static void getJson() {
		while (true) {
			try {
				Jokes jokes = restTemplate.getForObject(URL, Jokes.class);
				System.out.println(JsonUtils.asString(jokes));
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
