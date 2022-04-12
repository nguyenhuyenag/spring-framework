package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.QLTKRepository;
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

}
