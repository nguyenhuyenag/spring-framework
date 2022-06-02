package com.ts24.service;

import java.util.List;

import com.dto.SeekFilter;

public interface TS24TVANGuiHoaDonService {

	List<SeekFilter> findByMaTDiep(String matdiep);
	
	List<SeekFilter> findByTuNgayDenNgay(String tungay, String denngay);

}
