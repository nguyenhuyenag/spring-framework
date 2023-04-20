package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Dao;
import com.entity.second.Bar;
import com.service.BarService;

@Service
public class BarServiceImpl implements BarService {

	@Autowired
	private Dao<Bar> barDao;

	@Override
	public List<Bar> getAll() {
		return barDao.getAll();
	}

}
