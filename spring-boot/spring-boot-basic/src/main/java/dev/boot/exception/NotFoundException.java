package dev.boot.exception;

import java.io.Serial;

// (2)
public class NotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 6537119546278948799L;

	public NotFoundException(String message) {
		super(message);
	}

}
