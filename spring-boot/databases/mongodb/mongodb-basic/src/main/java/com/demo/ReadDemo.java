package com.demo;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ReadDemo {

	public static void read() {
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("db_name");
			MongoCollection<Document> collection = database.getCollection("department");
			Document doc = new Document("_id", 10);
			System.out.println(collection.find(doc).first().toJson());
		}
	}

	public static void getList() {
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("db_name");
			MongoCollection<Document> collection = database.getCollection("department");
			Iterator<Document> itr = collection.find().iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next().toJson());
			}
		}
	}

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		// getOne();
		getList();
	}

}

