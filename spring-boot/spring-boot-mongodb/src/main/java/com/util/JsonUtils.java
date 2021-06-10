package com.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;

public class JsonUtils {

	public static <T> List<T> toList(String jsonStringArray, Class<T> classOfT) {
		List<T> list = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(jsonStringArray);
		for (Object json : jsonArray) {
			T t = new Gson().fromJson(json.toString(), classOfT);
			list.add(t);
		}
		return list;
	}

}
