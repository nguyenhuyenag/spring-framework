package com.recaptcha.v2;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
    https://www.google.com/recaptcha/admin?hl=en
 */
public class ReCaptchaV2Utils {

    // re-captcha (v2)
    public static final String SITE_KEY = "6LegE2ktAAAAAKv3Rp8qQ4NheB17j300RJfltjPo";
    public static final String SECRET_KEY = "6LegE2ktAAAAAPH5yYvtIm3Mx4PzUN5VZ8idImIb";

    public static final String G_RECAPTCHA_RESPONSE = "g-recaptcha-response";
    public static final String RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean validateToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", ReCaptchaV2Utils.SECRET_KEY);
        requestMap.add("response", token);

        ReCaptchaV2Response response = restTemplate.postForObject(
            RECAPTCHA_ENDPOINT, requestMap, ReCaptchaV2Response.class
        );
        if (response == null) {
            return false;
        }

        // System.out.println(response.toString());
        return response.getSuccess();
    }

}
