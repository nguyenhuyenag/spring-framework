package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonUtils() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Object to JSON
	 */
	public static <T> String toJSON(T object) {
		if (Objects.nonNull(object)) {
			try {
				return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * InputStream to Object
	 */
	public static <T> T readValue(InputStream is, Class<T> type) {
		if (Objects.nonNull(is)) {
			try {
				return MAPPER.readValue(is, type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Map<String, String> asMap(String json) {
		if (StringUtils.isNotEmpty(json)) {
			try {
				return MAPPER.readValue(json, new TypeReference<Map<String, String>>() {});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Collections.emptyMap();
	}

}
