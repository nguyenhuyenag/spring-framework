package com.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class URLQuery {

	// Request param using Apache
	public static void paramsUsingApache() throws URISyntaxException {
		String url = "https://www.youtube.com/watch";
		URI uri = new URIBuilder(url) //
				.addParameter("v", "3AtDnEC4zak")//
				.addParameter("t", "20")//
				.build();
		System.out.println(uri.toString());
	}

	// Request param using Spring Framework
	public static void paramsUsingSpring() {
		String url = "https://www.youtube.com/watch";
		URI uri = UriComponentsBuilder.fromUriString(url) //
				.queryParam("v", "3AtDnEC4zak") //
				.queryParam("t", "20") //
				.build() //
				.toUri();
		System.out.println(uri);
	}

	protected static void buildPath() {
		// @formatter:off
		String basePath = "/products/{id}/attributes/{attributeId}";
		UriComponents builder = UriComponentsBuilder.fromPath(basePath) //
				.path("/my-path")			// Cần phải thêm '/'
				.pathSegment("more-path") 	// Có sẵn '/'
				.buildAndExpand(2, 13);
		// @formatter:on
		System.out.println(builder.toUriString());
	}

	public static void main(String[] args) throws Exception {
		// paramsUsingApache();
		paramsUsingSpring();
		// buildPath();
	}

}
