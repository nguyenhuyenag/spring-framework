package com.publisher;

import com.event.EventAsync;
import com.event.EventBasic;
import com.event.EventByApplicationEvent;
import com.util.DatetimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/*-
    When the object we’re publishing is not an ApplicationEvent,
    Spring will automatically wrap it in a PayloadApplicationEvent for us.
 */
@Component
@RequiredArgsConstructor
public class PublisherEvent {

    private final ApplicationEventPublisher applicationEventPublisher;

    // Publishing event created by extending ApplicationEvent
    public void publishEvent(final String message) {
        EventByApplicationEvent event = new EventByApplicationEvent(this, message);
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", method=publishEvent"
                + ",\t\t\tPublic message: " + message);
        applicationEventPublisher.publishEvent(event);
    }

    // Publishing an object as an event
    public void publishEventBasic(final String message) {
        EventBasic event = new EventBasic(message);
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", method=publishEventBasic"
                + ",\tPublic message: " + message);
        applicationEventPublisher.publishEvent(event);
    }

    // Publishing an object as an event
    public void publishEventAsync(final String message) {
        EventAsync event = new EventAsync(message);
        System.out.println(DatetimeUtils.now()
                + ", Thread=" + Thread.currentThread().getName()
                + ", method=publishEventAsync"
                + ",\tPublic message: " + message);
        applicationEventPublisher.publishEvent(event);
    }

}
