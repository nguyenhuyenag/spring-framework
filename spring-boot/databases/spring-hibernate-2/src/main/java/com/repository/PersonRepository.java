package com.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	@Query(value = "select Now()", nativeQuery = true)
	public Date getMySqlDateTimeCur();
	
}
