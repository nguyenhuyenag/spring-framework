package com.builder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {

    private String url;
    private HttpEntity body;
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequestBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpRequestBuilder addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public HttpRequestBuilder withBody(HttpEntity body) {
        this.body = body;
        return this;
    }

    public HttpPost build() {
        HttpPost httpPost = new HttpPost(url);
        // Set header
        if (!headers.isEmpty()) {
            headers.forEach(httpPost::setHeader);
        }
        // Set body
        if (body != null) {
            httpPost.setEntity(body);
        }
        return httpPost;
    }

//    public HttpGet build() {
//        HttpGet httpGet = new HttpGet(url);
//        headers.forEach(httpGet::setHeader);
//        // Set body if needed
//        return httpGet;
//    }

}
