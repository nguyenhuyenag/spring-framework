package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Jokes;

@Repository
public interface JokesRepository extends JpaRepository<Jokes, Integer> {

	@Query(value = "SELECT t.* FROM jokes t ORDER BY RAND() LIMIT 1;", nativeQuery = true)
	Jokes getOne();
	
}
