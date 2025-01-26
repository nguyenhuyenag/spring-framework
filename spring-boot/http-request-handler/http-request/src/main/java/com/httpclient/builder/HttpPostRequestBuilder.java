package com.httpclient.builder;

import com.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpPostRequestBuilder {

    private String url;
    private HttpEntity body;
    private HttpPost httpPost;
    private final Map<String, String> headers = new HashMap<>();

    private HttpPostRequestBuilder() {

    }

    // Factory method để tạo instance của HttpPostRequestBuilder
    public static HttpPostRequestBuilder builder() {
        return new HttpPostRequestBuilder();
    }

    public HttpPostRequestBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpPostRequestBuilder addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public HttpPostRequestBuilder withBody(HttpEntity body) {
        this.body = body;
        return this;
    }

    public HttpPostRequestBuilder build() {
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("URL cannot be null or empty");
        }
        this.httpPost = new HttpPost(url);
        this.headers.forEach(httpPost::setHeader);
        if (body != null) {
            httpPost.setEntity(body);
        }
        return this;
    }

    public <T> T execute(Class<T> responseType) {
        // @formatter:off
        try (
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost);
        ) {
            HttpEntity responseEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (responseEntity != null && statusCode == 200) {
                try (InputStream is = responseEntity.getContent()) {
                    return GsonUtils.readValue(is, responseType);
                }
            } else {
                log.warn("Unexpected response status: {}", statusCode);
            }
        } catch (IOException e) {
            log.error("An I/O error occurred: {}", e.getMessage(), e);
        }
        return null;
        // @formatter:on
    }

}
