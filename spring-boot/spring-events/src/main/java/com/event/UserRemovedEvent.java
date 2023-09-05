package com.event;

import lombok.Getter;

@Getter
public class UserRemovedEvent {

	private String message;

	public UserRemovedEvent(String message) {
		this.message = message;
	}

}
