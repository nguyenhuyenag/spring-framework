package com.hcaptcha;

import com.recaptcha.ReCaptchaResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
    Site Ket: https://dashboard.hcaptcha.com/sites/?page=1&archived=active
    Secret Key -> https://dashboard.hcaptcha.com/settings/secrets
 */
public class HCaptchaUtils {

    public static final String SITE_KEY = "734e3ba4-16a2-46d8-9086-674cdeea4df4";
    public static final String SECRET_KEY = "ES_bd869771d5a6410d837ea5e0ac154477";

    public static final String H_CAPTCHA_RESPONSE = "h-captcha-response";
    private static final String HCAPTCHA_ENDPOINT = "https://api.hcaptcha.com/siteverify";

    public static boolean validateToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        // Tạo payload gửi tới Cloudflare
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", SECRET_KEY);
        requestMap.add("response", token);

        ReCaptchaResponse response = restTemplate.postForObject(HCAPTCHA_ENDPOINT, requestMap, ReCaptchaResponse.class);

        if (response == null) {
            return false;
        }

        return response.getSuccess();
    }

}
