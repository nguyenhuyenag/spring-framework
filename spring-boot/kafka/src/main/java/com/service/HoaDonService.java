package com.service;

import java.util.List;

import com.model.HoaDon;

public interface HoaDonService {

	void reset(); // update tinhtrang_gui = 0

	void updateTinhTrangGui(String guid);

	List<HoaDon> findAllWithLimit(int limit);

}
