package com.demo;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertDemo {

	public static void insert1() {
		// Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("db_name");
			MongoCollection<Document> collection = database.getCollection("department");

			Document doc1 = new Document();
			doc1.append("_id", 10);
			doc1.append("dept_no", "D10");
			doc1.append("dept_name", "ACCOUNTING");
			doc1.append("location", "NEW YORK");
			collection.insertOne(doc1);

			Document doc2 = new Document();
			doc2.append("_id", 20);
			doc2.append("dept_no", "D20");
			doc2.append("dept_name", "RESEARCH");
			doc2.append("location", "DALLAS");
			doc2.append("description", "First department");
			collection.insertOne(doc2);

			Document doc3 = new Document();
			doc3.append("_id", 30);
			doc3.append("dept_no", "D30");
			doc3.append("dept_name", "SALES");
			doc3.append("location", "CHICAGO");
			collection.insertOne(doc3);

			Document doc4 = new Document();
			doc4.append("_id", 40);
			doc4.append("dept_no", "D40");
			doc4.append("dept_name", "OPERATIONS");
			doc4.append("location", "BOSTON");
			collection.insertOne(doc4);
			
			// collection.insertMany();

			System.out.println("Insert OK!");
		}
	}

	public static void insert2() {
//		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("db_name");
			MongoCollection<Document> collection = database.getCollection("class_room_collection");
			Document student = new Document("_id", new ObjectId());
			Random rand = new Random();
			student.append("student_id", 10000d) //
					.append("class_id", 1d) //
					.append("scores",
							Arrays.asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
									new Document("type", "quiz").append("score", rand.nextDouble() * 100),
									new Document("type", "homework").append("score", rand.nextDouble() * 100)));
			collection.insertOne(student);
			System.out.println("OK");
		}
	}

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		insert1();
		// insert2();
	}

}
