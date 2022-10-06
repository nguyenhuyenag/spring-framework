package com.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

@Repository
@Transactional
public interface VocabRepository extends JpaRepository<Vocabulary, Integer> {

	//@Query(value = "select min(id) from vocab", nativeQuery = true)
	//Integer findMinId();

	//@Query(value = "select lower(v.word) from vocab v", nativeQuery = true)
	//List<String> findAllVocab();
	
	List<Vocabulary> findAllByOrderByWord();

	// @Query(value = "select v.* from vocab v where v.word = :pword", nativeQuery = true)
	Vocabulary findByWord(String word);
	
	@Query(value = "select t.* from vocab t where t.word = :key or t.translate like \"%:key%\"", nativeQuery = true)
	Vocabulary advanceSearchByWord(String key);
	
	@Query(value = "select t.* from vocab where t.word like %:word%", nativeQuery = true)
	Page<Vocabulary> searchByWord(String word, Pageable pageable);
	
	@Query(value = "select t.* from vocab t order by rand() limit 1", nativeQuery = true)
	Vocabulary getRandomWord();
	
	// Lấy n dòng đầu tiên có count <= pcount
	@Query(value = "select t.* from vocab t where t.count <= :pcount order by t.count limit :n", nativeQuery = true)
	List<Vocabulary> getListVocabLimitByCount(@Param("pcount") int pcount, @Param("n") int n);
	
	@Query(value = "select word from vocab where pronounce = \"\" or translate = \"\" order by word", nativeQuery = true)
	List<String> incomplete();
	
	@Query(value = "select t.* from vocab t where pronounce = \"\" or translate = \"\" order by word", nativeQuery = true)
	List<Vocabulary> incompleteVocabulary();
	
	@Modifying
	@Query(value = "delete from vocab t where t.word = :word", nativeQuery = true)
	void deleteByWord(@Param("word") String word);

}
