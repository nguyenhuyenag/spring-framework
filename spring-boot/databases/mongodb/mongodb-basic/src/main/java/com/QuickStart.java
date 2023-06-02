package com;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@SuppressWarnings("unused")
public class QuickStart {

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");) {
			MongoDatabase database = mongoClient.getDatabase("mydb");
			MongoCollection<Document> collection = database.getCollection("test");

			// Insert one
			Document doc = new Document("name", "MongoDB");
			doc.append("type", "database");
			doc.append("count", 1);
			doc.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"));
			doc.append("info", new Document("x", 203).append("y", 102));
			// collection.insertOne(doc);

			// Insert many
			List<Document> list = new ArrayList<>();
			for (int i = 6; i < 20; i++) {
				list.add(new Document("i", i));
			}
			// collection.insertMany(list);

			// Count documents in a collection
			// System.out.println(collection.countDocuments());

			// Find the first document
			Document myDoc = collection.find().first();
			// System.out.println(myDoc.toJson(MyConstants.prettyPrint));

			// Find all document
			FindIterable<Document> cursor = collection.find();
			for (Document d : cursor) {
				// System.out.println(d.toJson());
			}

			// Find equal (=)
			myDoc = collection.find(eq("i", 4)).first(); // =
			// System.out.println(myDoc.toJson());

			// Find greater (>)
			cursor = collection.find(gt("i", 10));
			for (Document d : cursor) {
				// System.out.println(d.toJson());
			}

			// Find less than or equal (<=)
			collection.find(and(gt("i", 10), lte("i", 15)));
			for (Document d : cursor) {
				// System.out.println(d.toJson());
			}

			// Update a single docmuent
			UpdateResult update = collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)));
			System.out.println(update);

			// Delete a single document
			DeleteResult deleteResult = collection.deleteOne(eq("i", 0));
			// System.out.println(deleteResult.getDeletedCount());

			Bson filter = gte("i", 10);
			DeleteResult deleteAllResult = collection.deleteMany(filter);
			System.out.println(deleteAllResult.getDeletedCount());

			// Create indexes
			// 1 là tăng dần, -1 là giảm dần
			collection.createIndex(new Document("i", 1));
		}
	}

}
