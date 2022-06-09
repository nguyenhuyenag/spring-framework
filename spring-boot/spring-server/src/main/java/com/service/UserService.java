package com.service;

import com.entity.User;

public interface UserService {

	User findByUsername(String username);

}
