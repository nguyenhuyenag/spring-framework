package com.service;

import java.util.List;

import com.entity.User;

public interface QuanLyTaiKhoanService {

	List<User> getAll();

	boolean changeUserStatus(String email);

}
