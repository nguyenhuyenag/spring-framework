package com.service;

import java.util.List;

import com.entity.User;

public interface JDBCTemplateService {

	void insert();

	boolean deleteById(int id);

	int findMaxAge();

	User findByName(String name);

	List<User> findAll();

	List<String> findAllName();
	
	void batchUpdate();

}
