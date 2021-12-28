package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoadDataSourceException extends RuntimeException {
	
	private static final long serialVersionUID = -7775473946922833121L;

	public LoadDataSourceException(Throwable cause) {
		super("Could not load DataSource! - " + cause.getMessage());
	}
}
