package com.builder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {

    private String url;
    private HttpEntity body;

    private HttpGet httpGet;
    private HttpPost httpPost;

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

    public HttpRequestBuilder withHttpGet() {
        this.httpGet = new HttpGet(url);
        return this;
    }

    public HttpRequestBuilder withHttpPost() {
        this.httpPost = new HttpPost(url);
        return this;
    }

    public HttpRequestBuilder build() {
        // Set header
        if (!headers.isEmpty()) {
            headers.forEach(httpPost::setHeader);
        }
        // Set body
        if (body != null) {
            httpPost.setEntity(body);
        }
        return this;
    }

}
