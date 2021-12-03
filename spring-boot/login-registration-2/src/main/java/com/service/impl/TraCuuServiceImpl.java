package com.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.TTCN;
import com.repository.TTCNRepository;
import com.service.TraCuuService;

@Service
public class TraCuuServiceImpl implements TraCuuService {
	
	@Autowired
	private TTCNRepository repository;

	@Override
	public List<TTCN> tracuu(String masobhxh, String hoten, String diachilh) {
		
		if(StringUtils.isNotEmpty(masobhxh) && StringUtils.isNotEmpty(hoten) && StringUtils.isNotEmpty(diachilh)) {
			return repository.tracuu(masobhxh, hoten, diachilh);
		}else if (StringUtils.isNotEmpty(masobhxh) && StringUtils.isNotEmpty(hoten)) {
			return repository.tracuuMaVaTen(masobhxh, hoten);
		}else if(StringUtils.isNotEmpty(masobhxh) && StringUtils.isNotEmpty(diachilh)) {
			return repository.tracuuMaVaDiaChi(masobhxh, diachilh);
		}else if(StringUtils.isNotEmpty(hoten) && StringUtils.isNotEmpty(diachilh)) {
			return repository.tracuuTenVaDiaChi(hoten, diachilh);
		}else if(StringUtils.isNotEmpty(masobhxh)) {
			return repository.tracuuMaso(masobhxh);
		}else if(StringUtils.isNotEmpty(hoten)) {
				return repository.tracuuHoTen(hoten);
		}else if(StringUtils.isNotEmpty(diachilh)) {
				return repository.tracuuDiaChi(diachilh);
		}else {
			return null ;
		}
	//	return repository.tracuu(masobhxh, hoten, diachilh);
	}

}
