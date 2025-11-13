package com.urlbuilder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/*
    (1) Theo RFC 3986, các ký tự reserved được phép xuất hiện trong query string, gồm:

        ! * ' ( ) ; : @ & = + $ , / ? % # [ ]

    (2) Trong query string, chỉ các ký tự [A–Z a–z 0–9 - _ . ~] là thực sự an toàn, không cần encode.
    Các ký tự khác (&, @, =, +, %, #, ?, /, space, …) nên được encode nếu là một phần của dữ liệu chứ
    không phải dấu ngăn cách.
 */
public class URLQuery {

    // Request param using Apache
    public static void paramsUsingApache() throws URISyntaxException {
        String url = "https://www.youtube.com/watch";
        URI uri = new URIBuilder(url) //
                .addParameter("v", "3AtDnEC4zak") //
                .addParameter("t", "20") //
                .addParameter("email", "a&b@abc.com") // Auto encode
                .build();
        System.out.println(uri.toString());
    }

    // Request param using Spring Framework
    public static void paramsUsingSpring() {
        URI uri = UriComponentsBuilder.fromUriString("https://www.youtube.com/watch")
                .queryParam("v", "3AtDnEC4zak")
                .queryParam("t", "20")
                .queryParam("email", "a&b@abc.com") // Truyền giá trị gốc, không encode
                .encode() // Để Spring encode đúng
                .build()
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

    public static void test() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        // @formatter:off
        builder.setScheme("http").setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "httpclient")
                .setParameter("btnG", "Google Search")
                .setParameter("aq", "f")
                .setParameter("oq", "");
		// @formatter:on
        URI uri = builder.build();
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());
    }

    public static void main(String[] args) throws Exception {
        paramsUsingApache();
        paramsUsingSpring();
        // buildPath();
        // test();
    }

}
