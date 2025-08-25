package dev.util;

import java.lang.reflect.Type;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Slf4j
public class JsonUtils {

	// private static final Gson GSON = new Gson();
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonUtils() {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * Object to JSON String
	 * @param <T>    generic type
	 * @param object Java object
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

	/**
	 * JSON to Object
	 * @return Java object
	 */
	public static <T> T toObject(String jsonString, Type type) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.enableComplexMapKeySerialization().create();
		return gson.fromJson(jsonString, type);
	}

	public static com.fasterxml.jackson.databind.JsonNode toJsonNode(String jsonString) {
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
	 * Convert JSON string to JSONObject
	 * 
	 * @param jsonString is a JSON string
	 * @return JSONObject
	 * @see {@link JSONObject#get("fieldname")}}
	 */
	public static org.json.JSONObject toJSONObject(String jsonString) {
		return new JSONObject(jsonString);
	}
	
	/**
     * Common method to read JSON from different sources
     */
    private static <T> T readValueInternal(Object source, Class<T> type) {
        try {
            if (source == null) {
                return null;
            }
            if (source instanceof InputStream) {
                return MAPPER.readValue((InputStream) source, type);
            } else if (source instanceof String) {
                return MAPPER.readValue((String) source, type);
            }
        } catch (IOException e) {
            log.error("Error reading value from source", e);
        }
        return null;
    }

    /**
     * Read InputStream and convert to object of type T
     */
    public static <T> T readValue(InputStream is, Class<T> type) {
        return readValueInternal(is, type);
    }

    /**
     * Read JSON string and convert to object of type T
     */
    public static <T> T readValue(String json, Class<T> type) {
        return readValueInternal(json, type);
    }

}
