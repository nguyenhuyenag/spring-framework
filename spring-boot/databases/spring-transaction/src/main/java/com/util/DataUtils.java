package com.util;

import com.entity.User;

public class DataUtils {

	public static User passUser() {
		User u1 = new User();
		u1.setEmail("abc@abc.com");
		u1.setName("James");
		return u1;
	}

	public static User failUser() {
		User u2 = new User();
		u2.setName("transaction@test.com");
		u2.setName("Test");
		return u2;
	}

}
