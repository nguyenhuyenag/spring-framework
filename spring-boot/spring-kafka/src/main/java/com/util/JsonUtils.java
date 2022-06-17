//package com.util;
//
//import java.lang.reflect.Type;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//public class JsonUtils {
//
//	private static final ObjectMapper MAPPER = new ObjectMapper();
//
//	private JsonUtils() {
//		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	}
//	
//	public static String toJSON(Object object) {
//		if (object != null) {
//			try {
//				return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
//		return "";
//	}
//	
//	public static <T> T toObject(String jsonString, Type type) {
//		GsonBuilder builder = new GsonBuilder();
//		Gson gson = builder.enableComplexMapKeySerialization().create();
//		return gson.fromJson(jsonString, type);
//	}
//
//}
