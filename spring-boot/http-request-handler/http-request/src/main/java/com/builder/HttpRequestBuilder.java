package com.builder;

import com.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpRequestBuilder {

    private String url;
    private HttpGet httpGet;
    private HttpPost httpPost;
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

    public HttpRequestBuilder withHttpPost() {
        this.httpPost = new HttpPost(url);
        this.httpGet = null;
        return this;
    }

    public HttpRequestBuilder withHttpGet() {
        this.httpGet = new HttpGet(url);
        this.httpPost = null;
        return this;
    }

    public HttpRequestBuilder build() {
        // Apply headers to the appropriate HTTP method
        headers.forEach((key, value) -> {
            if (httpGet != null) {
                httpGet.setHeader(key, value);
            }
            if (httpPost != null) {
                httpPost.setHeader(key, value);
            }
        });
        // Set body if applicable
        if (httpPost != null && body != null) {
            httpPost.setEntity(body);
        }
        return this;
    }

    public <T> T execute(Class<T> responseType) {
        // @formatter:off
        try (
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet != null ? httpGet : httpPost)
        ) {
            HttpEntity responseEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200 && responseEntity != null) {
                try (InputStream is = responseEntity.getContent()) {
                    return GsonUtils.readValue(is, responseType);
                }
            } else {
                log.error("Request failed with status code: {}", statusCode);
            }
            // Consume the response entity to release resources
            EntityUtils.consume(responseEntity);
        } catch (IOException e) {
            log.error("An I/O error occurred: {}", e.getMessage(), e);
        }
        return null;
        // @formatter:on
    }

}
