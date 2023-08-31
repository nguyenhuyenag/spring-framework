package com.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * By default spring events are synchronous
 */
@Component
public class CustomSpringEventListener2 {

	// @Async
	@EventListener
	public void handleReturnedEvent(CustomSpringEvent event) {
		System.out.println("[" + this.getClass().getSimpleName() + "] Received: " + event.getMessage());
	}

}
