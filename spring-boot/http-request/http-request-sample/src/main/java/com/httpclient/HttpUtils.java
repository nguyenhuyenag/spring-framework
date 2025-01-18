package com.httpclient;

import com.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtils {

    public static <T> T createPostMethod(String url, Map<String, String> headers, HttpEntity entity, Class<T> type) {
        HttpPost httpPost = new HttpPost(url);
        // Add headers
        if (headers != null) {
            headers.forEach(httpPost::setHeader);
        }
        // Add body, params
        if (entity != null) {
            httpPost.setEntity(entity);
        }
        // @formatter:off
        try (
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost);
        ) {
            HttpEntity responseEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (responseEntity != null && statusCode == HttpStatus.SC_OK) {
                try (InputStream is = responseEntity.getContent()) {
                    return JsonUtils.readValue(is, type);
                }
            } else {
                log.warn("Unexpected response status: {}", statusCode);
            }
            EntityUtils.consume(responseEntity);
        } catch (IOException e) {
            log.error("An I/O error occurred: {}", e.getMessage(), e);
        }
        // @formatter:on
        return null;
    }

    private String postWithUrlEncodedForm() {
        // Create headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("secret_key", "<your_secret_key>");

        // Create data-urlencode
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("code", "<your_oauth_code>"));
        params.add(new BasicNameValuePair("app_id", "<your_app_id>"));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code_verifier", "your_code_verifier"));
        try {
            String url = "";
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            String response = createPostMethod(url, headers, entity, String.class);
            if (response != null) {

            }
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported encoding exception occurred: {}", e.getMessage(), e);
        }
        return null;
    }

    public void postWithJson() {
        // Create headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");

        // Create body
        Map<String, String> templateData = new HashMap<>();
        templateData.put("active_code", "<your_code>");
        try {
            String url = "";
            StringEntity entity = new StringEntity(JsonUtils.toJson(templateData));
            String response = createPostMethod(url, headers, entity, String.class);
            if (response != null) {

            }
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported encoding exception occurred: {}", e.getMessage(), e);
        }
    }

}
