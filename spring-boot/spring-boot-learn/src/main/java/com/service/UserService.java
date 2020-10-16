package com.service;

public interface UserService {

	void init();

	long count();

	boolean existsById(long id);

	void deleteById(long id);

	// long countByLastname(String lastname);

}
