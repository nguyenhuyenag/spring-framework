package com.httpclient.builder;

import com.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class HttpRequestBuilder<T extends HttpRequestBase, B extends HttpRequestBuilder<T, B>> {

    protected String url;
    protected final Map<String, String> headers = new HashMap<>();

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    public B withUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        this.url = url;
        return self();
    }

    public B addHeader(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Header key and value cannot be null");
        }
        headers.put(key, value);
        return self();
    }

    protected abstract T buildRequest();

    public <R> R execute(Class<R> responseType) {
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("URL must be set before execution");
        }

        T request = buildRequest();
        headers.forEach(request::setHeader);

        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(request)
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
    }
}

