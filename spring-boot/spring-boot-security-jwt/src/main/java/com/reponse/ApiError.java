package com.reponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

	private String timestamp;
	private int status;
	private String error; // Forbidden
	private String message;
	private String path;

}
