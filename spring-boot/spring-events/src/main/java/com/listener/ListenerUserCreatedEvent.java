package com.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.event.UserCreatedEvent;

/**
 * Nếu khai báo 2 Listener thì cả 2 sẽ cùng nhận được message.
 */
@Component
public class ListenerUserCreatedEvent implements ApplicationListener<UserCreatedEvent> {

	@Override
	public void onApplicationEvent(UserCreatedEvent event) {
		System.out.println("[" + this.getClass().getSimpleName()
				+ "] UserCreatedEvent received: " + event.getMessage());
	}

}
