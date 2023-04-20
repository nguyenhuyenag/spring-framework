package com.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DeleteDemo {

	public static boolean activityExists(MongoDatabase db, ObjectId id) {
		FindIterable<Document> iterable = db.getCollection("activity").find(new Document("_id", id));
		return iterable.first() != null;
	}

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("db_name");
			MongoCollection<Document> collection = database.getCollection("class_room_collection");
			Document doc = new Document("_id", "5fdee390f767db5f0851ab7b");
			// Document doc = new Document("_id", new ObjectId("5fdee390f767db5f0851ab7b"));
			if (collection.find(doc).first() != null) {
				collection.deleteOne(doc);
				System.out.println("OK");
			} else {
				System.out.println("Not found!");
			}
		}
	}

}
