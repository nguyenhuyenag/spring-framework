# Spring Boot MongoDB

	- query.fields().include("name");
	
	- Document <-> POJO
	
	- Criteria
	
	- collection.createIndex(new Document("i", 1));
	
- Find all documents where the _id field is not an integer

	db.getCollection("book").find({ "_id": { "$not": { "$type": 16 } } });

