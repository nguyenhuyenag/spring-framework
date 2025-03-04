package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Object to JSON String
     *
     * @param object is a Java object
     * @return JSON
     */
    public static String toJson(Object object) {
        if (object != null) {
            try {
                return MAPPER.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.error("Failed to convert object to JSON: {}", object, e);
            }
        }
        return "";
    }

}
