package com.service;

import java.util.List;

import com.entity.User;
import com.request.RegisterRequest;

public interface UserService {

	List<User> loadAll();

	void register(RegisterRequest dto);

}
