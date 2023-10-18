package com.util;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.web.util.UriComponentsBuilder;

public class EncodeDecodeURL {

	protected static URI encodeValue(String url) {
		return UriComponentsBuilder.fromUriString(url) //
				.build() //
				.encode() //
				.toUri();
	}

	protected static String decode(String value) throws Exception {
		return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
	}

	public static void encodeURL() throws Exception {
		String url = "http://www.example.com/CEREC® Materials & Accessories/IPS Empress® CAD.pdf";
		// (1) Encode & Decode
		URI encodeValue = encodeValue(url);
		System.out.println(encodeValue);
		System.out.println(decode(encodeValue.toString()));
		// (2)
		URL _url = new URL(url);
		URI uri = new URI(_url.getProtocol(), _url.getUserInfo(), _url.getHost(), _url.getPort(), //
				_url.getPath(), _url.getQuery(), _url.getRef());
		System.out.println(uri.toASCIIString());
	}

	public static void main(String[] args) throws Exception {
		encodeURL();
	}

}
