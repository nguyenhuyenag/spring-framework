package com.service;

import java.util.List;

import com.model.HoaDon;

public interface HoaDonService {

	void reset();

	void onSuccess(HoaDon hoadon);
	
	// void saveHistory(String mathongdiep);

	List<HoaDon> findAllWithLimit(int limit);

}
