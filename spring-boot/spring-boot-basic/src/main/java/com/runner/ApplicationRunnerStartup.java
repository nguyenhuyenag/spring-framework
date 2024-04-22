package com.runner;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.boot.bean.BeanUtilsOfSpring;

@Component
public class ApplicationRunnerStartup implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(new Date() + " [ApplicationRunnerStartup] Run method");
		 BeanUtilsOfSpring.testCopyProperties();
		// BeanUtilsOfApache.testCopyProperties();
	}

}
