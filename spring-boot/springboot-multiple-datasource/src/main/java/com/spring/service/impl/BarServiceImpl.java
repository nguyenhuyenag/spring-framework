package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.Dao;
import com.spring.entity.second.Bar;
import com.spring.service.BarService;

@Service
public class BarServiceImpl implements BarService {

	@Autowired
	private Dao<Bar> barDao;

	@Override
	public List<Bar> getAll() {
		return barDao.getAll();
	}

}
