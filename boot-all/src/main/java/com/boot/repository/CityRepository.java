package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boot.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	@Query("SELECT c FROM City c ORDER BY c.name")
	List<City> getAllByQuery();

}
