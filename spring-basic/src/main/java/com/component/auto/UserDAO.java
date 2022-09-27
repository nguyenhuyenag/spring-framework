package com.component.auto;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	public void findUser(int id) {
		System.out.println("Find user by id = " + id);
	}

}
