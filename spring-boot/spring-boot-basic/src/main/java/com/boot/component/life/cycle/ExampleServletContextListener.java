package com.boot.component.life.cycle;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

@Component
public class ExampleServletContextListener implements ServletContextListener {

	@Override
	// Context Initialised
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(new Date() + " [ExampleServletContextListener] Context Initialised");
	}

	@Override
	// Context shutdown
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(new Date() + " [ExampleServletContextListener] Context Shutdown");
	}

}
