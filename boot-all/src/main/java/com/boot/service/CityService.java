package com.boot.service;

import java.util.List;

import com.boot.entity.City;

public interface CityService {

	List<City> findAll();
	
	List<City> findAllByQuery();

}
