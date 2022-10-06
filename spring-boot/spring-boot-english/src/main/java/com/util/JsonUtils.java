package com.util;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonUtils() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static <T> String collectionToJSON(Collection<T> collection) {
		try {
			return MAPPER.writeValueAsString(collection);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
