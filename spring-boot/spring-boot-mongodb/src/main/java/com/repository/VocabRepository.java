package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

//@Transactional
@Repository
public interface VocabRepository extends MongoRepository<Vocabulary, String> {

	Optional<Vocabulary> findByWord(String word);

	List<Vocabulary> findByCountBetween(int from, int to);

	// JSON query methods
	@Query("{ 'name' : ?0 }")
	Optional<Vocabulary> findByWordUsingJSON(String word);

	@Query("{ 'count' : { $gte: ?0, $lte: ?1 } }")
	List<Vocabulary> findBetweenByJSON(int from, int to);

}
