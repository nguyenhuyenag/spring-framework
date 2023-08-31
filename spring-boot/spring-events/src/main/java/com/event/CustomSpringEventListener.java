package com.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Nếu khai báo 2 CustomSpringEventListener thì cả 2 sẽ cùng nhận được message
 */
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

	@Override
	public void onApplicationEvent(CustomSpringEvent event) {
		System.out.println("[" + this.getClass().getSimpleName() + "] Received: " + event.getMessage());
	}

}
