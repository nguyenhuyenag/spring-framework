package com.cloudflare;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
    https://www.cloudflare.com/application-services/products/turnstile/
 */
public class CloudflareUtils {

    public static final String SITE_KEY = "0x4AAAAAAA5itUt-VM5fdxP8";
    public static final String SECRET_KEY = "0x4AAAAAAA5itUEfNyrew-6fidVDDnWXBLE";

    public static final String CF_TURNSTILE_RESPONSE = "cf-turnstile-response";
    private static final String TURNSTILE_ENDPOINT = "https://challenges.cloudflare.com/turnstile/v0/siteverify";

    public static boolean validateToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        // Tạo payload gửi tới Cloudflare
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", SECRET_KEY);
        requestMap.add("response", token);

        TurnstileResponse response = restTemplate.postForObject(TURNSTILE_ENDPOINT, requestMap, TurnstileResponse.class);

        if (response == null) {
            return false;
        }

        return response.isSuccess(); // Trả về trạng thái xác thực
    }

}
