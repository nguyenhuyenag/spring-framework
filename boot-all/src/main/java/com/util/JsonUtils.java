package com.util;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static final ObjectMapper MAPPER = new ObjectMapper();

	public JsonUtils() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Convert Object to JSON
	 * @param <T> generic type
	 * @param object Java object
	 * @return JSON
	 */
	public static <T> String writeAsString(T object) {
		if (Objects.nonNull(object)) {
			try {
				return MAPPER.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}

}
