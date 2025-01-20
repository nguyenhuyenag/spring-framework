package httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpUtils {

    public static <T> T createPostRequest(String url, Map<String, String> headers, HttpEntity body, Class<T> type)
            throws NoSuchAlgorithmException, KeyStoreException {
        HttpPost httpPost = new HttpPost(url);
        // Add headers
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // Add body, params
        if (body != null) {
            httpPost.setEntity(body);
        }
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, TrustAllStrategy.INSTANCE);
        // @formatter:off
        try (
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLContext(builder.build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
            CloseableHttpResponse response = httpClient.execute(httpPost);
        ) {
            HttpEntity responseEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (responseEntity != null && statusCode == HttpStatus.SC_OK) {
                try (InputStream is = responseEntity.getContent()) {
                    return JsonUtils.readValue(is, type);
                }
            }
            EntityUtils.consume(responseEntity);
        } catch (IOException | KeyManagementException e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
        // @formatter:on
        return null;
    }

    public static void postWithJson() {
        // Create headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // Create body
        Map<String, String> templateData = new HashMap<>();
        templateData.put("access_token", "<your_code>");

        try {
            String url = "https://business.openapi.zalo.me/message/template";
            StringEntity entity = new StringEntity(JsonUtils.toJson(templateData));
            ZaloResponse response = createPostRequest(url, headers, entity, ZaloResponse.class);
            if (response != null) {
                System.out.println();
                System.out.println("=========================================");
                System.out.println("Is JDK 7: " + SystemUtils.IS_JAVA_1_7);
                System.out.println("Call API successfully");
                System.out.println("Message: " + response.getMessage());
                System.out.println("=========================================");
                System.out.println();
            }
        } catch (NoSuchAlgorithmException | KeyStoreException | IOException e) {
            log.error("Unsupported encoding exception occurred: {}", e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        postWithJson();
    }

}
