package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

@Repository
public interface VocabRepository extends JpaRepository<Vocabulary, Integer> {

	@Query(value = "select min(id) from vocab", nativeQuery = true)
	Integer findMinId();

	@Query(value = "select max(id) from vocab", nativeQuery = true)
	Integer findMaxId();

	@Query(value = "select lower(v.word) from vocab v", nativeQuery = true)
	List<String> findAllVocab();

	// @Query(value = "select v.* from vocab v where v.word = :word", nativeQuery = true)
	Vocabulary findByWord(String word);

}
