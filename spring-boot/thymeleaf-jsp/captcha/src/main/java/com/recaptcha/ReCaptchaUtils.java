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

//	public static JsonObject readValue(InputStream is) {
//		if (is != null) {
//			try (JsonReader jsonReader = Json.createReader(is)) {
//				return jsonReader.readObject();
//			}
//		}
//		return null;
//	}

//	public static boolean verify(String gRecaptchaResponse) {
//		if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
//			return false;
//		}
//
//		try {
//			URL verifyUrl = new URL(RECAPTCHA_ENDPOINT);
//
//			// Mở một kết nối (Connection) tới URL trên.
//			HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
//
//			// Thêm các thông tin Header vào Request chuẩn bị gửi tới server.
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
//			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//			// Dữ liệu sẽ gửi tới Server.
//			String postParams = "secret=" + Constants.SECRET_KEY + "&response=" + gRecaptchaResponse;
//
//			// Send Request
//			conn.setDoOutput(true);
//
//			// Lấy Output Stream (Luồng đầu ra) của kết nối tới Server.
//			// Ghi dữ liệu vào Output Stream, có nghĩa là gửi thông tin đến Server.
//			OutputStream outStream = conn.getOutputStream();
//			outStream.write(postParams.getBytes());
//
//			outStream.flush();
//			outStream.close();
//
//			// Mã trả lời được trả về từ Server.
//			int responseCode = conn.getResponseCode();
//			System.out.println("responseCode=" + responseCode);
//
//			InputStream is = conn.getInputStream();
//			JsonObject jsonObject = JsonUtils.readValue(is, JsonObject.class);
//			// ==> {"success": true}
//			System.out.println("Response: " + jsonObject);
//
//            return jsonObject.getBoolean("success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

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
