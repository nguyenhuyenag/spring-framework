package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	// Native SQL
	@Query(value = "select * from City", nativeQuery = true)
	List<City> getAllCity();

}
