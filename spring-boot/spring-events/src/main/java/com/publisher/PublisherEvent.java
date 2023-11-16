package com.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.event.UserRemovedEvent;

/**
 * When the object we’re publishing is not an ApplicationEvent, Spring will
 * automatically wrap it in a PayloadApplicationEvent for us
 */
@Component
public class PublisherEvent {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishCustomEvent(final String message) {
		// Publishing an object as an event
		applicationEventPublisher.publishEvent(new UserRemovedEvent("Public message from UserRemovedEvent=" + message));
		
		// Publishing event created by extending ApplicationEvent
		// applicationEventPublisher.publishEvent(new UserCreatedEvent(this, "Public message from UserCreatedEvent=" + message));
	}

}
