package com.boot.runonce;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MyService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean] This method runs automatically once after the bean is initialized.");
    }

}
