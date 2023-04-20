package com.pojo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import static com.mongodb.client.model.Filters.*;

public class PojoMain {

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString("mongodb://localhost:27017")) //
				.codecRegistry(codecRegistry) //
				.build();
		try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
			MongoDatabase db = mongoClient.getDatabase("sample_training");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);

			// insert
			Grade newGrade = new Grade();
			newGrade.setStudentId(10003d);
			newGrade.setClassId(10d);
			newGrade.setScores(Collections.singletonList(new Score("homework", 50d)));
			// grades.insertOne(newGrade);

			// find()
			Grade grade = grades.find(eq("student_id", 10003d)).first();
			System.out.println("Grade found: " + grade);

			// findOneAndReplace()
			List<Score> newScores = new ArrayList<>(grade.getScores());
			newScores.add(new Score("exam", 42d));
			grade.setScores(new ArrayList<>(grade.getScores()));
			Document filter = new Document("_id", grade.getId());
			FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions() //
															.returnDocument(ReturnDocument.AFTER);
			Grade updatedGrade = grades.findOneAndReplace(filter, grade, returnDocAfterReplace);
			System.out.println("Grade replaced: " + updatedGrade);
			
			// delete()
			// System.out.println(grades.deleteOne(filter));
		}
	}

}
