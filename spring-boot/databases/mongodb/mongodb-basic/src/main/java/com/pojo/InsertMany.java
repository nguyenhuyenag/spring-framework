package com.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

public class InsertMany {

	static String id = "5fe54d726c6be435045b659";

	public static void insertOne(MongoCollection<Document> collection) {
		Document doc = new Document("_id", new ObjectId(id + 5));
		doc.append("text", RandomStringUtils.randomAlphabetic(5).toUpperCase());
		collection.insertOne(doc);
	}

	public static void insertMany(MongoCollection<Document> collection) {
		List<Document> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Document doc = new Document("_id", new ObjectId(id + i));
			doc.append("text", RandomStringUtils.randomAlphabetic(5).toUpperCase());
			list.add(doc);
		}
		collection.insertMany(list);
	}

	public static void insertManyOptions(MongoCollection<Document> collection) {
		List<Document> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Document doc = new Document("_id", new ObjectId(id + i));
			doc.append("text", RandomStringUtils.randomAlphabetic(5).toUpperCase());
			list.add(doc);
		}
		collection.insertMany(list, new InsertManyOptions().ordered(false));
	}

	/**
	 * Mặc định MongoDB sẽ insert danh sách theo thứ tự, nếu có 1 Document bị lỗi
	 * (ví dụ DuplicateKeyException) thì phần còn lại của danh sách sẽ không được
	 * insert. Thuộc tính `new InsertManyOptions().ordered(false)` cho phép tiếp tục
	 * insert các Document (không có lỗi) còn lại.
	 */
	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
			MongoCollection<Document> collection = sampleTrainingDB.getCollection("insert_many");

			// insertOne(collection);

			// insertMany(collection);

			insertManyOptions(collection);

			// collection.drop();

			System.out.println("OK!");
		}

	}

}
