package com.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class URLBuilder {

	public static void main(String[] args) throws URISyntaxException {
		String url = "https://www.youtube.com/watch";
		URI uri = new URIBuilder(url) //
				.addParameter("v", "3AtDnEC4zak")//
				.addParameter("t", "20s")//
				.build();
		System.out.println(uri.toString());
	}

}
