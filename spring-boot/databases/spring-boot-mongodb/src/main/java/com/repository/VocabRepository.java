package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Vocabulary;

@Repository
//@Transactional
public interface VocabRepository extends MongoRepository<Vocabulary, String> {

	Optional<Vocabulary> findByWord(String word);

	List<Vocabulary> findByCountBetween(int from, int to);

	// JSON query methods
	@Query("{ 'name' : ?0 }")
	Optional<Vocabulary> findByWordUsingJSON(String word);

	@Query("{ 'count' : { $gte: ?0, $lte: ?1 } }")
	List<Vocabulary> findBetweenByJSON(int from, int to);
	
	// 1: asc, -1: desc
	@Query(value = "{count : 97}", sort = "{word : -1}")
	List<Vocabulary> findAndSortByJSON();

	// TODO
	@Query("{$or : [{word: ?0}, {word : ?1}]}")
	List<Vocabulary> findWithORConditons(String startWith1, String startWith2);

	// TODO
	@Query("{$and : [{$or : [{noOfPages: {$gt: 275}}, {noOfPages : {$lt: 200}}]}, {$or : [{id: {$gt: 103}}, {id : {$lt: 102}}]}]}")
	List<Vocabulary> findWithANDConditions();

	// Regex
	@Query("{ 'word' : { $regex: ?0 } }")
	List<Vocabulary> findWithRegex(String regex);

}
