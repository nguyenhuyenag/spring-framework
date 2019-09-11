package com.boot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public JSONUtils() {
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String objectToJSON(Object object) throws JsonProcessingException {
		return OBJECT_MAPPER.writeValueAsString(object);
	}

}
