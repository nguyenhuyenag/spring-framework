package com.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

@Repository
@Transactional
public interface VocabRepository extends JpaRepository<Vocabulary, Integer> {

	// JPQL
	@Query(value = "SELECT t FROM Vocabulary t WHERE t.word IN :words")
	List<Vocabulary> findVocabByWordList(@Param("words") List<String> words);

	// SQL
	@Modifying
	@Query(value = "update Vocabulary t set t.lastModified = now() where t.word = :word")
	void updateLastModified(String word);

}
