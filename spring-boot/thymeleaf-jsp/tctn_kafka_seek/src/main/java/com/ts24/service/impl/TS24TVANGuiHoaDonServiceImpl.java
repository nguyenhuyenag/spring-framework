package com.ts24.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.SeekFilter;
import com.ts24.entity.TS24TVANGuiHoaDon;
import com.ts24.repository.TS24TVANGuiHoaDonRepository;
import com.ts24.service.TS24TVANGuiHoaDonService;

@Service
public class TS24TVANGuiHoaDonServiceImpl implements TS24TVANGuiHoaDonService {

	@Autowired
	private TS24TVANGuiHoaDonRepository ts24TVANGuiHoaDonRepository;
	
	private List<SeekFilter> toSeek(List<TS24TVANGuiHoaDon> list) {
		List<SeekFilter> result = new ArrayList<>();
		for (TS24TVANGuiHoaDon hoadon : list) {
			result.add(new SeekFilter(hoadon.getMatdiep(), hoadon.getMaloaiTdiep(), hoadon.getNgayguiTct()));
		}
		return result;
	}
	
	@Override
	public List<SeekFilter> findByMaTDiep(String matdiep) {
		List<TS24TVANGuiHoaDon> listHoaDon = ts24TVANGuiHoaDonRepository.findByMaTDiepAndTinhTrangGui(matdiep, 1);
		return toSeek(listHoaDon);
	}

	@Override
	public List<SeekFilter> findByTuNgayDenNgay(String tungay, String denngay) {
		List<TS24TVANGuiHoaDon> listHoaDon = ts24TVANGuiHoaDonRepository.findByTinhTrangGuiTuNgayDenNgay(tungay, denngay, 1);
		return toSeek(listHoaDon);
	}

}
