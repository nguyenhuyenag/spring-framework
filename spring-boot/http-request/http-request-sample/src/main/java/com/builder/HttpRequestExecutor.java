package com.builder;

import com.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class HttpRequestExecutor<T> {

    private HttpPost httpPost;
    private Class<T> responseType;

    public HttpRequestExecutor<T> withHttpPost(HttpPost httpPost) {
        this.httpPost = httpPost;
        return this;
    }

    public HttpRequestExecutor<T> withResponseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    public T execute() {
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(httpPost)) {

            HttpEntity responseEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();

            if (responseEntity != null && statusCode == 200) { // HttpStatus.SC_OK = 200
                try (InputStream is = responseEntity.getContent()) {
                    // Replace JsonUtils.readValue with your JSON parsing logic
                    return JsonUtils.readValue(is, responseType);
                }
            } else {
                log.warn("Unexpected response status: {}", statusCode);
            }
            EntityUtils.consume(responseEntity);
        } catch (IOException e) {
            log.error("An I/O error occurred: {}", e.getMessage(), e);
        }
        return null;
    }

}
