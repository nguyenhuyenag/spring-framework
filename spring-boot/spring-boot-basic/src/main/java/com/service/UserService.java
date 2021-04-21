package com.service;

import com.entity.User;

public interface UserService {

	int init();

	long count();
	
	void deleteById(int id);

	boolean existsById(int id);
	
	User getById(Integer id);

	// long countByLastname(String lastname);

}
