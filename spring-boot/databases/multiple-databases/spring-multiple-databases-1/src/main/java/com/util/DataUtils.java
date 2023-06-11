package com.util;

import com.primary.entity.Address;
import com.second.entity.User;

public class DataUtils {

	public static User passUser() {
		User u1 = new User();
		u1.setEmail("james@abc.com");
		u1.setName("James");
		return u1;
	}

	public static User failUser() {
		User u2 = new User();
		u2.setName("transaction@test.com");
		u2.setName("Test");
		return u2;
	}

	public static Address passAddress() {
		Address a1 = new Address();
		a1.setAddress("Ho Chi Minh");
		a1.setCountry("Viet Nam");
		return a1;
	}

	public static Address failAddress() {
		Address a2 = new Address();
		a2.setAddress("London");
		a2.setCountry("United Kingdom");
		return a2;
	}

}
