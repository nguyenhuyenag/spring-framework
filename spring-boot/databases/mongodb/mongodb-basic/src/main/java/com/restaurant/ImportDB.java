package com.restaurant;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import common.util.FilesUtils;
import common.util.JsonUtils;
import common.util.PathUtils;

@SuppressWarnings("unused")
public class ImportDB {

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString("mongodb://localhost:27017")) //
				.codecRegistry(codecRegistry) //
				.build();

		try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
			MongoDatabase db = mongoClient.getDatabase("restaurant_db");
			MongoCollection<Restaurant> collection = db.getCollection("restaurant", Restaurant.class);

			Path dir = Paths.get(Paths.get(PathUtils.HOME, "file", "restaurant.txt").toString());
			List<String> list = FilesUtils.readAllLines(dir);
			list.forEach(t -> {
				Restaurant rest = JsonUtils.toObject(t, Restaurant.class);
				// collection.insertOne(rest);
			});

			// delete all
			// collection.deleteMany(new Document());
			
			System.out.println("OK!");
		}
	}

}
