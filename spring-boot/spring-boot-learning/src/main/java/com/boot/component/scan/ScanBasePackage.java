package com.boot.component.scan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = { "learn.of.component.scan" })
public class ScanBasePackage {

	// Sử dụng SpringApplication.run() trong Application.java
	public static void main() {
		ApplicationContext context = SpringApplication.run(ScanBasePackage.class);
		try {
			Car girl = context.getBean(Car.class);
			System.out.println("Bean: " + girl.toString());
		} catch (Exception e) {
			System.out.println("Bean Car không tồn tại");
		}

		try {
			OtherCar otherGirl = context.getBean(OtherCar.class);
			if (otherGirl != null) {
				System.out.println("Bean: " + otherGirl.toString());
			}
		} catch (Exception e) {
			System.out.println("Bean OtherCar không tồn tại");
		}
	}

}
