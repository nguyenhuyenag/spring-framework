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
    private final Map<String, String> headers = new HashMap<>();

    // Private constructor to enforce usage of the builder method
    private HttpPostRequestBuilder() {
    }

    // Factory method to create a new instance of the builder
    public static HttpPostRequestBuilder builder() {
        return new HttpPostRequestBuilder();
    }

    public HttpPostRequestBuilder withUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        this.url = url;
        return this;
    }

    public HttpPostRequestBuilder addHeader(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Header key and value cannot be null");
        }
        headers.put(key, value);
        return this;
    }

    public HttpPostRequestBuilder withBody(HttpEntity body) {
        this.body = body;
        return this;
    }

    public <T> T execute(Class<T> responseType) {
        // @formatter:off
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("URL must be set before execution");
        }

        HttpPost httpPost = new HttpPost(url);
        headers.forEach(httpPost::setHeader);

        if (body != null) {
            httpPost.setEntity(body);
        }

        try (
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost)
        ) {
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                try (InputStream is = responseEntity.getContent()) {
                    if (statusCode == 200) {
                        return GsonUtils.readValue(is, responseType);
                    } else {
                        log.warn("Unexpected response: status={}", statusCode);
                    }
                }
            } else {
                log.warn("Empty response received for URL: {}", url);
            }
        } catch (IOException e) {
            log.error("An error occurred while executing the request: {}", e.getMessage(), e);
        }
        return null;
        // @formatter:on
    }

}
