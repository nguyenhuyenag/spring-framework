package com.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;

public class RequestBuilder {

	private Map<String, Object> parameters = new HashMap<>();

	public RequestBuilder add(String paramName, Object paramValue) {
		this.parameters.put(paramName, paramValue);
		return this;
	}

	public String build() {
		URIBuilder uriBuilder = new URIBuilder();
		for (Entry<String, Object> param : this.parameters.entrySet()) {
			uriBuilder.addParameter(param.getKey(), param.getValue().toString());
		}
		return uriBuilder.toString();
	}

	public static void main(String[] args) {
		int min = 1;
		String site = "abc";
		String params = new RequestBuilder().add("order", "desc").add("sort", "votes").add("min", min).add("site", site)
				.build();
		System.out.println("https://api.stackexchange.com/2.1/questions" + params);
	}
}
