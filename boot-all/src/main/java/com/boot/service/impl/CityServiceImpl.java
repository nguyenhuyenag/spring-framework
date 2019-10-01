package com.boot.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.entity.City;
import com.boot.repository.CityRepository;
import com.boot.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> findAll() {
		long start = System.currentTimeMillis();
		List<City> list = cityRepository.findAll();
		Collections.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
		long end = System.currentTimeMillis();
		System.out.println("findAll");
		System.out.println(end - start);
		return list;
	}

	@Override
	public List<City> findAllByQuery() {
		long start = System.currentTimeMillis();
		List<City> list = cityRepository.getAllByQuery();
		long end = System.currentTimeMillis();
		System.out.println("findAllByQuery");
		System.out.println(end - start);
		return list;
	}

}
