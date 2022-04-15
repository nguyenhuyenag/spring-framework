package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.QLTKRepository;
import com.request.EditUserRequest;
import com.service.QuanLyTaiKhoanService;

@Service
public class QuanLyTaiKhoanServiceImpl implements QuanLyTaiKhoanService {

	@Autowired
	private QLTKRepository qltkRepository;

	@Override
	public List<User> getAll() {
		List<User> list = qltkRepository.findAll();
		list.forEach(t -> t.setPassword(null));
		return list;
	}

	@Override
	public boolean changeUserStatus(String email) {
		User user = qltkRepository.findByEmail(email);
		if (user != null) {
			user.setActive(user.getActive() == 0 ? 1 : 0);
			return qltkRepository.save(user) != null;
		}
		return false;
	}

	@Override
	public boolean editUser(EditUserRequest request) {
		return false;
	}

}
