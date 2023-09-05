package com.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.event.UserRemovedEvent;

@Component
public class ListenerUserRemovedEvent {

	// @Async
	@EventListener
	public void handleReturnedEvent(UserRemovedEvent event) {
		System.out.println("[" + this.getClass().getSimpleName() + "] Received: " + event.getMessage());
	}

}
