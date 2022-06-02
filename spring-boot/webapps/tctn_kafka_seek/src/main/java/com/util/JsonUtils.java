package com.util;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
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
				return MAPPER.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

}
