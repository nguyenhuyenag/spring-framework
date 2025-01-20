package com.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Slf4j
public class GsonUtils {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object object) {
        if (object != null) {
            try {
                return GSON.toJson(object);
            } catch (Exception e) {
                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
            }
        }
        return "";
    }

    public static <T> T readValue(InputStream is, Class<T> type) {
        if (is != null) {
            try (Reader reader = new InputStreamReader(is)) {
                return GSON.fromJson(reader, type);
            } catch (Exception e) {
                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
            }
        }
        return null;
    }

//    private static final ObjectMapper MAPPER = new ObjectMapper();
//
//    private JsonUtils() {
//        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }

//    /**
//     * Object to JSON String
//     */
//    public static String asString(Object object) {
//        if (object != null) {
//            try {
//                return MAPPER.enable(SerializationFeature.WRAP_ROOT_VALUE) // wrapping is enabled
//                        .writerWithDefaultPrettyPrinter() //
//                        .writeValueAsString(object);
//            } catch (JsonProcessingException e) {
//                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
//            }
//        }
//        return "";
//    }

//    public static String toJson(Object object) {
//        if (object != null) {
//            try {
//                return MAPPER.writerWithDefaultPrettyPrinter() //
//                        .writeValueAsString(object);
//            } catch (JsonProcessingException e) {
//                log.error("Error occurred while processing JSON: {}", e.getMessage(), e);
//            }
//        }
//        return "";
//    }
//
//    public static <T> T readValue(InputStream is, Class<T> type) {
//        if (is != null) {
//            try {
//                return MAPPER.readValue(is, type);
//            } catch (IOException e) {
//                log.error("An I/O error occurred: {}", e.getMessage(), e);
//            }
//        }
//        return null;
//    }

//    public static <T> T readValue(String json, Class<T> type) {
//        if (StringUtils.isNotEmpty(json)) {
//            try {
//                return MAPPER.readValue(json, type);
//            } catch (IOException e) {
//                log.error("An I/O error occurred: {}", e.getMessage(), e);
//            }
//        }
//        return null;
//    }

}
