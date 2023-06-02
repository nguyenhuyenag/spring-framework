package com;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;

public class ListDatabases {

	public static void list1() {
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoIterable<String> itrmg = mongoClient.listDatabaseNames();
			MongoCursor<String> itr = itrmg.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
		}
	}
	
	public static void list2() {
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			List<Document> list = mongoClient.listDatabases().into(new ArrayList<>());
			list.forEach(t -> System.out.println(t.toJson()));
		}
	}

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		// list1();
		list2();
	}

}
