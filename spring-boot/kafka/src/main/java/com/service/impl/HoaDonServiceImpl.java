package com.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.HoaDon;
import com.repository.HoaDonRepository;
import com.service.HoaDonService;

@Service
public class HoaDonServiceImpl implements HoaDonService {

	@Autowired
	private HoaDonRepository repository;

	@Override
	public void reset() {
		repository.reset();
	}

	@Override
	public void updateTinhTrangGui(String guid) {
		repository.updateTinhTrangGui(guid);
	}

	@Override
	public List<HoaDon> findAllWithLimit(int limit) {
		return repository.findAllWithLimit(limit);
	}

}
