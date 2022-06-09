package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Jokes;

@Repository
public interface JokesRepository extends JpaRepository<Jokes, Integer> {
	
}
