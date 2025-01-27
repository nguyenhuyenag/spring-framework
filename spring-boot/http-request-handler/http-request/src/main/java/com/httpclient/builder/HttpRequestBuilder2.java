//package com.httpclient;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.HttpPost;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class HttpRequestBuilder {
//
//    private String url;
//    private Map<String, String> headers = new HashMap<>();
//    private HttpEntity body;
//
//    public HttpRequestBuilder withUrl(String url) {
//        this.url = url;
//        return this;
//    }
//
//    public HttpRequestBuilder addHeader(String key, String value) {
//        headers.put(key, value);
//        return this;
//    }
//
//    public HttpRequestBuilder withBody(HttpEntity body) {
//        this.body = body;
//        return this;
//    }
//
//    public HttpPost build() {
//        HttpPost httpPost = new HttpPost(url);
//        headers.forEach(httpPost::setHeader);
//        // Set body if needed
//        return httpPost;
//    }
//
//}
