package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
				return MAPPER.enable(SerializationFeature.WRAP_ROOT_VALUE) // wrapping is enabled
						.readValue(is, type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
