package com.service;

import java.util.List;

import com.entity.Vocabulary;
import com.request.InsertDTO;

public interface TemplateService {

	boolean isExists(String word);

	boolean remove(String word);

	boolean collectionExists();

	Vocabulary insert(InsertDTO dto);

	Vocabulary findOne(String word);

	// phuong thuc nay tra ve object truoc khi duoc sua doi
	Vocabulary findAndModify(InsertDTO dto);

	Vocabulary update(InsertDTO dto);

	List<Vocabulary> findAll();

	List<Vocabulary> findAllAndSort();

	// BSON & Document: doc.toJSON() & Document.parse(json)

//	Document insertAny(String jsonString);
//	List<Vocabulary> basicQuery(); // <- MongoDB `raw` query
//	List<Document> bsonFilter();
//	List<Document> bsonSort();

}
