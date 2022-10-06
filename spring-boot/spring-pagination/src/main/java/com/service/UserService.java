package com.service;

import java.util.List;
import java.util.Map;

import com.model.User;

public interface UserService {

	List<User> get(int page, int size, String sortby);
	
	Map<String, Object> info(int page, int size);
	
}
