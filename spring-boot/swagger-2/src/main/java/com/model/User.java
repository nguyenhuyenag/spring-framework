package com.model;

import javax.validation.constraints.NotNull;

public class User {

	@NotNull(message = "ID can't be null")
	private String id;
	
}
