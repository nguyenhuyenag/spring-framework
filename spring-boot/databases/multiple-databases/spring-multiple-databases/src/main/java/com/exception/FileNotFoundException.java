package com.exception;

public class FileNotFoundException extends FileException {

	private static final long serialVersionUID = 1L;

	public FileNotFoundException(String message) {
		super(message);
	}

	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
