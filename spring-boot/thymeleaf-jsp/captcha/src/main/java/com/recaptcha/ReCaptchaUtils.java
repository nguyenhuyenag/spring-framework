package com.recaptcha;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
    https://www.google.com/recaptcha/admin?hl=en
 */
public class ReCaptchaUtils {

    // re-captcha
    public static final String SITE_KEY = "6LcB3csaAAAAAASS2buZJ-olgepn_r7bb7zaNJsS";
    public static final String SECRET_KEY = "6LcB3csaAAAAAFaYAEKFun6zBqnkkXYebH4e_Yzh";

    public static final String G_RECAPTCHA_RESPONSE = "g-recaptcha-response";

    public static final String RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean validateToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", ReCaptchaUtils.SECRET_KEY);
        requestMap.add("response", token);

        ReCaptchaResponse response = restTemplate.postForObject(RECAPTCHA_ENDPOINT, requestMap, ReCaptchaResponse.class);
        if (response == null) {
            return false;
        }

        // System.out.println(response.toString());
        return response.getSuccess();
    }

}
