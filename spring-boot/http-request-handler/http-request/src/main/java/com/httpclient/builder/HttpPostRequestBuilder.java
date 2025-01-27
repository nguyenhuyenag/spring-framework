package com.httpclient.builder;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;

@Slf4j
public class HttpPostRequestBuilder extends HttpRequestBuilder<HttpPost, HttpPostRequestBuilder> {

    private HttpEntity body;

    public static HttpPostRequestBuilder builder() {
        return new HttpPostRequestBuilder();
    }

    public HttpPostRequestBuilder withBody(HttpEntity body) {
        this.body = body;
        return this;
    }

    @Override
    protected HttpPost buildRequest() {
        HttpPost httpPost = new HttpPost(url);
        if (body != null) {
            httpPost.setEntity(body);
        }
        return httpPost;
    }
}

