package com.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.LoginResponse;
import com.util.JsonUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Post {

    private static final Logger LOG = LoggerFactory.getLogger(Post.class);

    private static class Interceptor implements HttpRequestInterceptor {
        @Override
        public void process(final HttpRequest httpRequest, final HttpContext httpContext)
                throws HttpException, IOException {
            ServletRequestAttributes servletRequestAttributes
                    = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            String authHeader = servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

            // log.info("Header: {}", authHeader);
            if (StringUtils.hasText(authHeader)) {
                httpRequest.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
            }
        }
    }

    public static void postParams() throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8080/post-params");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "huyennv"));
        params.add(new BasicNameValuePair("password", "123456"));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpResponse response = client.execute(httpPost);
            System.out.println("Status:" + response.getStatusLine().toString());
            String content = EntityUtils.toString(response.getEntity());
            // InputStream is = response.getEntity().getContent(); // implements Closeable
            // String content = IOUtils.toString(is, StandardCharsets.UTF_8);
            System.out.println(content);
        }
    }

    public static void postJson() throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8082/auth/login");

        Map<String, String> map = new HashMap<>();
        map.put("email", "huyennv@abc.com");
        map.put("password", "123456");

        ObjectMapper mapper = new ObjectMapper();
        StringEntity entity = new StringEntity(mapper.writeValueAsString(map));
        httpPost.setEntity(entity);

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpResponse response = client.execute(httpPost);
            System.out.println("Status:" + response.getStatusLine().toString());
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            System.out.println(content);
        }
    }

    public static void postJson2() throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8082/auth/login");

        String json = "{\"email\": \"huyennv@huyennv.com\", \"password\": \"123456\"}";
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        HttpResponse response = httpClient.execute(httpPost);
        String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
        System.out.println(content);
    }

    public static void setTimeout() {
        // int timeout = 5000;
        // RequestConfig config = RequestConfig.custom()
        // .setConnectTimeout(timeout)
        // .setConnectionRequestTimeout(timeout)
        // .setSocketTimeout(timeout).build();
        String url = "http://localhost:8080/auth/login";

        Map<String, String> login = new HashMap<>();
        login.put("username", "huyennv");
        login.put("password", "123456");

        final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
        // try (CloseableHttpClient httpClient =
        // HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(JsonUtils.toJSON(login));
            httpPost.setEntity(entity);
            final HttpResponse response = httpClient.execute(httpPost);
            LOG.info("Status = {}", response.getStatusLine().getStatusCode());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static LoginResponse loginPost() {
        String url = "http://localhost:8080/auth/login";
        Map<String, String> data = new HashMap<>();
        data.put("username", "huyennv");
        data.put("password", "123456");
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            // StringEntity entity = new StringEntity(JsonUtils.toJSON(data));
            httpPost.setEntity(new StringEntity(JsonUtils.toJSON(data)));
            final HttpResponse response = httpClient.execute(httpPost);
            LOG.info("Status = {}", response.getStatusLine().getStatusCode());
            // System.out.println(EntityUtils.toString(response.getEntity()));
            return JsonUtils.readValue(response.getEntity().getContent(), LoginResponse.class);
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void postJWT() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            LoginResponse login = loginPost();
            HttpPost httpPost = new HttpPost("http://localhost:8080/v2/get-one");
            httpPost.setHeader("Authorization", "Bearer " + login.getToken());
            final HttpResponse response = httpClient.execute(httpPost);
            LOG.info("Status = {}", response.getStatusLine().getStatusCode());
            System.out.println(EntityUtils.toString(response.getEntity()));
            // EntityUtils.consume(response.getEntity());
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void postXWWWFormUrlencoded() throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8080/bsmsws.asmx/SendBrandSms");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "abc"));
        params.add(new BasicNameValuePair("password", "xxxxx"));
        params.add(new BasicNameValuePair("brandname", "TS24corp"));
        params.add(new BasicNameValuePair("loaitin", "1"));
        params.add(new BasicNameValuePair("phonenumber", "84960000000"));
        params.add(new BasicNameValuePair("message", "sms_content" + System.currentTimeMillis()));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        // httpPost.setHeader(HttpHeaders.CONTENT_TYPE,
        // MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpResponse response = client.execute(httpPost);
            System.out.println("Status:" + response.getStatusLine().getStatusCode());
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            System.out.println(content);
        }
    }

    public static void basicAuthentication() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/employees");
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "password"));
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider) //
                .build(); //
             CloseableHttpResponse response = httpClient.execute(request)) {
            // 401 if wrong user/password
            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        }
    }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public static void basicAuthenticationUsingHTTPHeaders() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/employees");
        request.setHeader(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("user1", "password"));
        // CredentialsProvider provider = new BasicCredentialsProvider();
        // provider.setCredentials(AuthScope.ANY, new
        // UsernamePasswordCredentials("user", "password"));
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build(); //
             CloseableHttpResponse response = httpClient.execute(request)) {
            // 401 if wrong user/password
            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        }

    }

    // completed method
    public static <T> T doPost(Object data, String url, Class<T> type) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(JsonUtils.toJSON(data));
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);
            LOG.info("Status: {}", response.getStatusLine().toString());
            InputStream is = response.getEntity().getContent();
            return JsonUtils.readValue(is, type);
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPost(Object data, String url) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(JsonUtils.toJSON(data));
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            LOG.info("Status = {}", response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity());
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        // postParams();
        // postJson();
        // postJson2();
        // setTimeout();
        // postJWT();
        // postXWWWFormUrlencoded();
        // basicAuthentication();
        // basicAuthenticationUsingHTTPHeaders();
        System.out.println();
    }

}
