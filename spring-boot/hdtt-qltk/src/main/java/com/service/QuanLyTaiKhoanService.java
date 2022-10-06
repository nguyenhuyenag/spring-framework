package com.service;

import java.util.List;

import com.entity.User;
import com.request.EditUserRequest;

public interface QuanLyTaiKhoanService {

	List<User> getAll();

	boolean changeUserStatus(String email);
	
	boolean editUser(EditUserRequest request);

}
