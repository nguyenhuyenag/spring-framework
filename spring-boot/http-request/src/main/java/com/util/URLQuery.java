package com.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class URLQuery {

	public static void usingApacheUriBuilder() throws URISyntaxException {
		String url = "https://www.youtube.com/watch";
		URI uri = new URIBuilder(url) //
				.addParameter("v", "3AtDnEC4zak")//
				.addParameter("t", "20")//
				.build();
		System.out.println(uri.toString());
	}

	public static void usingSpringUriComponentsBuilder() {
		String url = "https://www.youtube.com/watch";
		URI uri = UriComponentsBuilder.fromUriString(url) //
				.queryParam("v", "3AtDnEC4zak") //
				.queryParam("t", "20") //
				.build() //
				.toUri();
		System.out.println(uri.toString());
	}

	protected static URI encodeValue(String url) {
		return UriComponentsBuilder.fromUriString(url).build().encode().toUri();
	}

	protected static String decode(String value) throws Exception {
		return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
	}

	public static void encodeURL() throws Exception {
		String url = "http://www.example.com/CEREC® Materials & Accessories/IPS Empress® CAD.pdf";
		URI encodeValue = encodeValue(url);
		System.out.println(encodeValue);
		System.out.println(decode(encodeValue.toString()));
	}

	public static void main(String[] args) throws Exception {
		usingApacheUriBuilder();
		usingSpringUriComponentsBuilder();
		encodeURL();
	}

}
