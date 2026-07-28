package com.recaptcha.v3;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
    https://www.google.com/recaptcha/admin/create
 */
public class ReCaptchaV3Utils {

    public static final String SITE_KEY = "6LfxD2ktAAAAAHmJQFcYqy7Uebd8gBAVgFNkwQ3R";
    public static final String SECRET_KEY = "6LfxD2ktAAAAAJGet2MesY8X__BT6O2mF7Dz_eOI";

    public static final String G_RECAPTCHA_RESPONSE = "g-recaptcha-response";

    private static final String RECAPTCHA_ENDPOINT =
            "https://www.google.com/recaptcha/api/siteverify";

    public static ReCaptchaV3Response validateToken(String token) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("secret", SECRET_KEY);
        request.add("response", token);

        return restTemplate.postForObject(
                RECAPTCHA_ENDPOINT,
                request,
                ReCaptchaV3Response.class
        );
    }

}
