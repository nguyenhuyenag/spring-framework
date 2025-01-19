package com.builder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;

public class BuilderTest {

    public static void main(String[] args) {
        HttpEntity body = null;

        HttpPost httpPost = new HttpRequestBuilder()
                .withUrl("http://example.com")
                .addHeader("Authorization", "Bearer token")
                .addHeader("Content-Type", "application/json")
                .withBody(body)
                .build();

        HttpRequestExecutor<String> executor = new HttpRequestExecutor<String>()
                .withHttpPost(httpPost)
                .withResponseType(String.class);

        String result = executor.execute();
    }

}
