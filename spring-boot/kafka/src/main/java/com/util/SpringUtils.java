package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils {

	public static ApplicationContext CTX;

	@Autowired
	private void setApplicationContext(ApplicationContext applicationContext) {
		CTX = applicationContext;
	}

	public static <T> T getBean(Class<T> classT) {
		return CTX.getBean(classT);
	}

}