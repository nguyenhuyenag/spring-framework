package com.httpclient.builder;

import com.pojo.ZaloResponse;
import com.util.GsonUtils;
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
        HttpPostRequestBuilder postRequest = HttpPostRequestBuilder.builder()
                .withUrl(url)
                .addHeader("key", "value")
                .withBody(body)
                .build();

        ZaloResponse response = postRequest.execute(ZaloResponse.class);
        if (response != null) {
            System.out.println(response.getMessage());
        }
    }

}
