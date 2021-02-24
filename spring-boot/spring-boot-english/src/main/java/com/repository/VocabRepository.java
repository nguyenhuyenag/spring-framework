package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

@Repository
public interface VocabRepository extends JpaRepository<Vocabulary, Integer> {

	@Query(value = "select min(id) from dictionary", nativeQuery = true)
	Integer findMinId();

	@Query(value = "select max(id) from dictionary", nativeQuery = true)
	Integer findMaxId();

}
