package com.attyuttam.dynamicdatasourcerouting.application.exception;

public class EmployeeDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -388490319364971359L;

	public EmployeeDetailsNotFoundException(String message) {
		super(message);
	}

}
