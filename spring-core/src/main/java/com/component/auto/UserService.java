package com.component.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @Service("userService1")
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public void findUser(int id) {
		System.out.println("UserService find: ");
		userDAO.findUser(id);
	}

}
