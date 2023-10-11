package com.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class URLQuery {

	public static void whenUsingApacheUriBuilder() throws URISyntaxException {
		String url = "https://www.youtube.com/watch";
		URI uri = new URIBuilder(url) //
				.addParameter("v", "3AtDnEC4zak")//
				.addParameter("t", "20")//
				.build();
		System.out.println(uri.toString());
	}

	public static void whenUsingSpringUriComponentsBuilder() {
		String url = "https://www.youtube.com/watch";
		URI uri = UriComponentsBuilder.fromUriString(url) //
				.queryParam("v", "3AtDnEC4zak") //
				.queryParam("t", "20") //
				.build() //
				.toUri();
		System.out.println(uri.toString());
	}

	public static void main(String[] args) throws URISyntaxException {
		whenUsingApacheUriBuilder();
		whenUsingSpringUriComponentsBuilder();
	}

}
