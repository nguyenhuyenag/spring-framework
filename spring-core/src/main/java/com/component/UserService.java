package com.component;

public class UserService {
	
	private UserDAO userDAO;

	public void findUser(int id) {
		System.out.println("UserService find: ");
		userDAO.findUser(id);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
