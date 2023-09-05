package com.event;

import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8874223071665993075L;

	private String message;

	public UserCreatedEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
