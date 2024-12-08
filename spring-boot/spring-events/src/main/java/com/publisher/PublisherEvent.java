package com.publisher;

import com.util.DatetimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.event.CustomEvent;

/*-
    When the object we’re publishing is not an ApplicationEvent,
    Spring will automatically wrap it in a PayloadApplicationEvent for us.
 */
@Component
@RequiredArgsConstructor
public class PublisherEvent {

    private final ApplicationEventPublisher applicationEventPublisher;

    // Publishing an object as an event
    public void publishCustomEvent(final String message) {
        CustomEvent event = new CustomEvent(message);
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", class=" + this.getClass().getSimpleName()
                + ", \t\t\t\tPublic message: " + message);
        applicationEventPublisher.publishEvent(event);
    }

    public void publishCustomEvent2(final String message) {
        // Publishing event created by extending ApplicationEvent
        // applicationEventPublisher.publishEvent(new UserCreatedEvent(this, "Public message from UserCreatedEvent=" + message));
    }

}
