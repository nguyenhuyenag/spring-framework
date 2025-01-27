package com.httpclient.builder;

import com.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpGetRequestBuilder extends HttpRequestBuilder<HttpGet, HttpGetRequestBuilder> {

    public static HttpGetRequestBuilder builder() {
        return new HttpGetRequestBuilder();
    }

    @Override
    protected HttpGet buildRequest() {
        return new HttpGet(url);
    }
}

