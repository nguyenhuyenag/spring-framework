package com.tgdd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.SeekFilter;
import com.tgdd.entity.TGDDTVANGuiHoaDon;
import com.tgdd.repository.TGDDTVANGuiHoaDonRepository;
import com.tgdd.service.TGDDTVANGuiHoaDonService;

@Service
public class TGDDTVANGuiHoaDonServiceImpl implements TGDDTVANGuiHoaDonService {

	@Autowired
	private TGDDTVANGuiHoaDonRepository tgddTVANGuiHoaDonRepository;
	
	private List<SeekFilter> toSeek(List<TGDDTVANGuiHoaDon> list) {
		List<SeekFilter> result = new ArrayList<>();
		for (TGDDTVANGuiHoaDon hoadon : list) {
			result.add(new SeekFilter(hoadon.getMatdiep(), hoadon.getMaloaiTdiep(), hoadon.getNgayguiTct()));
		}
		return result;
	}
	
	@Override
	public List<SeekFilter> findByMaTDiep(String matdiep) {
		List<TGDDTVANGuiHoaDon> listHoaDon = tgddTVANGuiHoaDonRepository.findByMaTDiepAndTinhTrangGui(matdiep, 1);
		return toSeek(listHoaDon);
	}

	@Override
	public List<SeekFilter> findByTuNgayDenNgay(String tungay, String denngay) {
		List<TGDDTVANGuiHoaDon> listHoaDon = tgddTVANGuiHoaDonRepository.findByTinhTrangGuiTuNgayDenNgay(tungay, denngay, 1);
		return toSeek(listHoaDon);
	}

}
