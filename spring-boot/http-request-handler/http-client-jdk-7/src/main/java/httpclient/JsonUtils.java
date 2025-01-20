package httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

//    /**
//     * Object to JSON
//     */
//    public static <T> String toJSON(T object) {
//        if (object != null) {
//            try {
//                return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return "";
//    }

    public static String toJson(Object object) {
        if (object != null) {
            try {
                return MAPPER.writerWithDefaultPrettyPrinter() //
                        .writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
            }
        }
        return "";
    }

    /**
     * InputStream to Object
     */
    public static <T> T readValue(InputStream is, Class<T> type) {
        if (is != null) {
            try {
                return MAPPER.readValue(is, type);
            } catch (IOException e) {
                // e.printStackTrace();
                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
            }
        }
        return null;
    }

    public static <T> T readValue(String input, Class<T> type) {
        if (input != null) {
            try {
                return MAPPER.readValue(input, type);
            } catch (IOException e) {
                // e.printStackTrace();
                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
            }
        }
        return null;
    }

//    public static Map<String, String> asMap(String json) {
//        if (StringUtils.isNotEmpty(json)) {
//            try {
//                return MAPPER.readValue(json, new TypeReference<Map<String, String>>() {
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return Collections.emptyMap();
//    }

}
