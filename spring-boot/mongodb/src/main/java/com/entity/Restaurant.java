package com.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "restaurant") // neu ten class va collection khong giong nhau
public class Restaurant {

	@Id
	private String id;
	private String name;
	private String borough;
	private String cuisine;

	@Field(value = "restaurant_id")
	private String restaurantId;

	private Address address;
	private List<Grades> grades;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
