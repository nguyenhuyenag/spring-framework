package com.httpclient.builder;

import com.pojo.ZaloResponse;
import com.util.GsonUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BuilderTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // Build body
        Map<String, String> data = new HashMap<>();
        data.put("access_token", "<your_code>");
        StringEntity body = new StringEntity(GsonUtils.toJson(data));

        // Build POST request
        String url = "https://business.openapi.zalo.me/message/template";
        ZaloResponse result = HttpPostRequestBuilder.builder()
                .withUrl(url)
                .addHeader("Authorization", "Bearer token")
                .addHeader("Content-Type", "application/json")
                .withBody(body)
                .execute(ZaloResponse.class);

        if (result != null) {
            System.out.println(result.getMessage());
        }
    }

}
