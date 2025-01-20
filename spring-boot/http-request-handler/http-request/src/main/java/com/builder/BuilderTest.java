package com.builder;

import com.pojo.ZaloResponse;
import com.util.GsonUtils;
import org.apache.http.HttpEntity;
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
        HttpRequestBuilder httpRequest = new HttpRequestBuilder()
                .withUrl("https://business.openapi.zalo.me/message/template")
                .addHeader("Content-Type", "application/json")
                .withBody(body)
                .withHttpPost()
                .build();

//        HttpRequestExecutor<ZaloResponse> executor = new HttpRequestExecutor<ZaloResponse>()
//                .withHttpPost(httpPost)
//                .withResponseType(ZaloResponse.class);

//        ZaloResponse execute = executor.execute();
//        if (execute != null) {
//            System.out.println(execute.getError());
//        }
    }

}
