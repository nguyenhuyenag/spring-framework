package com.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {

	@Query(value = "select now()", nativeQuery = true)
	Date getMySQLDateTime();

}
