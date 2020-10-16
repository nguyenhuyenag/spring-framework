package com.boot.component.scan;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("learn.of.component.scan")
// @ComponentScan({"a.b.c","a.d.e"}) // multiple scan
public class ComponentScans {

	public static void call(String[] args) {

		ApplicationContext context = SpringApplication.run(ComponentScans.class, args);

		try {
			Car girl = context.getBean(Car.class);
			System.out.println("Bean: " + girl.toString());
		} catch (Exception e) {
			System.out.println("Bean Girl không tồn tại");
		}

		try {
			OtherCar otherGirl = context.getBean(OtherCar.class);
			if (otherGirl != null) {
				System.out.println("Bean: " + otherGirl.toString());
			}
		} catch (Exception e) {
			System.out.println("Bean Girl không tồn tại");
		}
	}

}
