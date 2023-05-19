package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonUtils() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Object to JSON
	 * 
	 * @param <T>    generic type
	 * @param object Java object
	 * @return JSON
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

	public static JsonNode toJsonNode(String jsonString) {
		try {
			return MAPPER.readTree(jsonString);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * InputStream to Object
	 * 
	 * @param is   InputStream
	 * @param type class type
	 * @return Object
	 */
	public static <T> T readValue(InputStream is, Class<T> type) {
		try {
			if (is != null) {
				return MAPPER.readValue(is, type);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * JSON to List Object
//	 * 
//	 * @param <T>
//	 * @param json
//	 * @param array
//	 * @return List<T>
//	 */
//	public static <T> List<T> toList(String json) {
//		try {
//			return MAPPER.readValue(json, new TypeReference<List<T>>() {});
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	public static <T> T readValue(BufferedReader br, Class<T> type) {
//		try {
//			if (br != null) {
//				return MAPPER.readValue(br, type);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
