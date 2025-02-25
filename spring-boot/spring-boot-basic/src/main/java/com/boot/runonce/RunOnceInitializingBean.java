package com.boot.runonce;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/*
    Phương thưc chỉ chạy 1 lần
 */
@Component
public class RunOnceInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("[InitializingBean] This method runs automatically once after the bean is initialized.");
    }

}
