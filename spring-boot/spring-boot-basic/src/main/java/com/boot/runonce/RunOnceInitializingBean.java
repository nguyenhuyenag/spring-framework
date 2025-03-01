package com.boot.runonce;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.Instant;

/*
    Phương thưc chỉ chạy 1 lần
 */
@Component
public class RunOnceInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println(Instant.now() + "[InitializingBean] This method runs automatically once after the bean is initialized.");
    }

}
