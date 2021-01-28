package com.service;

public interface UserService {

	int init();

	long count();

	boolean existsById(long id);

	void deleteById(long id);

	// long countByLastname(String lastname);

}
