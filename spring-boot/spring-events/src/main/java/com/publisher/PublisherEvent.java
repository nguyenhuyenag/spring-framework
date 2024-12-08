package com.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.event.UserRemovedEvent;

/**
 * When the object we’re publishing is not an ApplicationEvent, Spring will
 * automatically wrap it in a PayloadApplicationEvent for us.
 */
@Component
@RequiredArgsConstructor
public class PublisherEvent {

    // @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final String message) {
        // Publishing an object as an event
        UserRemovedEvent event = new UserRemovedEvent("Public message from UserRemovedEvent=" + message);
        applicationEventPublisher.publishEvent(event);

        // Publishing event created by extending ApplicationEvent
        // applicationEventPublisher.publishEvent(new UserCreatedEvent(this, "Public message from UserCreatedEvent=" + message));
    }

}
