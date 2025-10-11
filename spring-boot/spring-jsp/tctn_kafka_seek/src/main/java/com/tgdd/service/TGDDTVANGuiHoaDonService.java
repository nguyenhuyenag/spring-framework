package com.tgdd.service;

import java.util.List;

import com.dto.SeekFilter;

public interface TGDDTVANGuiHoaDonService {

	List<SeekFilter> findByMaTDiep(String matdiep);
	
	List<SeekFilter> findByTuNgayDenNgay(String tungay, String denngay);

}
