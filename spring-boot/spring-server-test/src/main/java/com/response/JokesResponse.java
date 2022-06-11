package com.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JokesResponse {

	private String type;
	private Value value;

	@Getter
	@Setter
	public static class Value {

		private int id = 0;
		private String joke;
		private List<String> categories;

	}

}
