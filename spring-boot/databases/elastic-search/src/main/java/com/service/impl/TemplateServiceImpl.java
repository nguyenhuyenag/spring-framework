package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.request.InsertDTO;
import com.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	private static final String WORD = "word";
	private static final String COUNT = "count";
	private static final String VOCABULARY = "vocabulary";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean isExists(String word) {
		Query query = new Query(Criteria.where(WORD).is(word.toLowerCase()));
		// mongoTemplate.exists(query, VOCABULARY);
		return mongoTemplate.exists(query, Vocabulary.class);
	}

	@Override
	public Vocabulary findOne(String word) {
		Query query = new Query(Criteria.where(WORD).is(word));
		mongoTemplate.findOne(query, Vocabulary.class);
		return mongoTemplate.findOne(query, Vocabulary.class);
	}

	@Override
	public Vocabulary insert(InsertDTO dto) {
		// is exist
		if (isExists(dto.getWord())) {
			return null;
		}
		Vocabulary entity = new Vocabulary(dto.getWord(), dto.getPronounce(), dto.getTranslate());
		// insert(objectToSave)
		// insert(objectToSave, collectionName)
		return mongoTemplate.insert(entity, VOCABULARY);
	}

	@Override
	public Vocabulary update(InsertDTO dto) {
		Vocabulary v = findOne(dto.getWord().toLowerCase());
		// not found
		if (v == null) {
			return null;
		}
		v.setPronounce(dto.getPronounce());
		v.setTranslate(dto.getTranslate());
		/**
		 * save(): Update the whole object, if '_id' is present, perform an update, else
		 * insert it
		 */
		return mongoTemplate.save(v);
	}

	@Override
	public boolean remove(String word) {
		Vocabulary v = findOne(word);
		// is exist
		if (v != null) {
			mongoTemplate.remove(v, VOCABULARY);
			return true;
		}
		return false;
	}

	@Override
	public List<Vocabulary> findAll() {
		// mongoTemplate.find(query, entityClass, collectionName)
		return mongoTemplate.findAll(Vocabulary.class);
	}

	@Override
	public List<Vocabulary> findAllAndSort() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "count"));
		List<Vocabulary> list = mongoTemplate.find(query, Vocabulary.class);
		return list.stream().limit(20).collect(Collectors.toList());
	}

	@Override
	public Vocabulary findAndModify(InsertDTO dto) {
		Query query = new Query(Criteria.where(WORD).is(dto.getWord()));
		Update update = new Update();
		update.set(COUNT, dto.getCount());
		// findAndModify(query, update, Vocabulary.class);
		return mongoTemplate.findAndModify(query, update, Vocabulary.class, VOCABULARY);
	}

//	@Override
//	public Document insertAny(String jsonString) {
//		Document doc = new Document();
//		JSONObject jsonObject = new JSONObject(jsonString);
//		if (isExists(jsonObject.getString("word"))) {
//			return null;
//		}
//		doc.append("word", jsonObject.get("word"));
//		doc.append("createBy", jsonObject.get("createBy"));
//		doc.append("pronounce", jsonObject.get("pronounce"));
//		doc.append("translate", jsonObject.get("translate"));
//		doc.append("createDate", new Date());
//		return mongoTemplate.insert(doc, "new_word");
//	}

	@Override
	public boolean collectionExists() {
		// mongoTemplate.collectionExists("collectionName");
		return mongoTemplate.collectionExists(Vocabulary.class);
	}

//	@Override
//	public List<Vocabulary> basicQuery() {
//		BasicQuery query = new BasicQuery("{ count : { $gt : 95 } }");
//		return mongoTemplate.find(query, Vocabulary.class);
//	}

//	@Override
//	public List<Document> bsonFilter() {
//		List<Document> list = new ArrayList<>();
//		try (MongoClient mongoClient = MongoClients.create();) {
//			MongoDatabase database = mongoClient.getDatabase("english");
//			MongoCollection<Document> collection = database.getCollection("vocabulary");
//
//			/**
//			 * Comparison
//			 */
//			// Bson filter = Filters.eq("word", "able");
//			// Bson filter = Filters.ne("count", 5);
//			// Bson filter = Filters.in("count", 90, 97);
//			// Bson filter = Filters.in("count", Arrays.asList(90, 97));
//			// tương tự cho `Filters.nin()`
//			// Bson filter = Filters.gt("count", 97);
//			// Bson filter = Filters.gte("count", 5);
//			// Bson filter = Filters.lt("count", 5);
//			// Bson filter = Filters.lte("count", 5);
//
//			/**
//			 * Logical
//			 */
//			Bson filter = Filters.and(gt("i", 10), lt("i", 15));
//			FindIterable<Document> cursor = collection.find(filter);
//			cursor.forEach(list::add);
//		}
//		return list;
//	}

//	@Override
//	public List<Document> bsonSort() {
//		List<Document> list = new ArrayList<>();
//		try (MongoClient mongoClient = MongoClients.create();) {
//			MongoDatabase database = mongoClient.getDatabase("english");
//			MongoCollection<Document> collection = database.getCollection("vocabulary");
//			Bson filter = Sorts.ascending("word");
//			// Bson filter = Sorts.descending("word");
//			FindIterable<Document> cursor = collection.find(filter);
//			cursor.forEach(list::add);
//		}
//		return list;
//	}

}
