package com.boot.runonce;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    Phương thưc chỉ chạy 1 lần
 */
@Component
public class RunOnePostConstruct {

    @PostConstruct
    public void init() {
        System.out.println("[PostConstruct] This method runs automatically once after the bean is initialized.");
    }

}
