package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.entity.Vocabulary;
//
//@Transactional
@Repository
public interface VocabRepository extends MongoRepository<Vocabulary, String> {

	Optional<Vocabulary> findByWord(String word);
	
	List<Vocabulary> findByCountBetween(int from, int to);

	// JSON query methods
	@Query("{ 'name' : ?0 }")
	Optional<Vocabulary> findByWordUsingJSON(String word);

}
