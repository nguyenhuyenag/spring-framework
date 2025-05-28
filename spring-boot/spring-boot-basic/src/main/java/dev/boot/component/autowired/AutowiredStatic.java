package dev.boot.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class AutowiredStatic {
	
	
	private static ResourceBundleMessageSource messageSource;

    private AutowiredStatic(@Autowired ResourceBundleMessageSource messageSource) {
    	AutowiredStatic.messageSource = messageSource;
    }

    public static void getMessage(String code) {
        System.out.println(messageSource.toString());
    }
	
}
