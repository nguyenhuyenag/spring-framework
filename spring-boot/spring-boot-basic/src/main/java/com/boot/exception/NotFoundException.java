package com.boot.exception;

// (2)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6537119546278948799L;

	public NotFoundException(String message) {
		super(message);
	}

}
