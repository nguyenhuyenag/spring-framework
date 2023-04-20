package com.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

public class UpdateDemo {

	public static void update() {
//		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("db_name");
			MongoCollection<Document> collection = database.getCollection("department");

			Bson filter = Filters.eq("_id", 10);
			Bson update = Updates.set("location", "TEXAS");

			UpdateResult updateResult = collection.updateOne(filter, update);

			System.out.println(collection.find(filter).first().toJson(prettyPrint));

			System.out.println(updateResult);

			System.out.println("Update OK!");
		}
	}

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		update();
	}

}
