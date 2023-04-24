package com.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restaurant")
public class Restaurant {

	@Id
	private ObjectId id;

	@Field("URL")
	private String url;
	private String address;

	@Field("address_line_2")
	private String addressLine2;

	private String name;
	private String outcode;
	private String postcode;
	private int rating = 0;

	@Field("type_of_food")
	private String typeOfFood;

}
