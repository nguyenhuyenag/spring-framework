//package com.httpclient.builder;
//
//import com.util.GsonUtils;
//import com.util.JsonUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@Slf4j
//public class HttpRequestExecutor {
//
////    public <T> T execute(HttpUriRequest request, Class<T> responseType) {
////        // @formatter:off
////        try (
////            CloseableHttpClient client = HttpClients.createDefault();
////            CloseableHttpResponse response = client.execute(request)
////        ) {
////            HttpEntity responseEntity = response.getEntity();
////            int statusCode = response.getStatusLine().getStatusCode();
////            if (responseEntity != null && statusCode == 200) {
////                try (InputStream is = responseEntity.getContent()) {
////                    return JsonUtils.readValue(is, responseType);
////                }
////            } else {
////                log.warn("Unexpected response status: {}", statusCode);
////            }
////            EntityUtils.consume(responseEntity);
////        } catch (IOException e) {
////            log.error("An I/O error occurred: {}", e.getMessage(), e);
////        }
////        return null;
////        // @formatter:on
////    }
//
//    public <T> T execute(HttpUriRequest request, Class<T> responseType) {
//        // @formatter:off
//        try (
//            CloseableHttpClient client = HttpClients.createDefault();
//            CloseableHttpResponse response = client.execute(request)
//        ) {
//            HttpEntity responseEntity = response.getEntity();
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (responseEntity != null && statusCode == 200) {
//                try (InputStream is = responseEntity.getContent()) {
//                    return GsonUtils.readValue(is, responseType);
//                }
//            } else {
//                log.warn("Unexpected response status: {}", statusCode);
//            }
//        } catch (IOException e) {
//            log.error("An I/O error occurred: {}", e.getMessage(), e);
//        }
//        return null;
//        // @formatter:on
//    }
//
//}
