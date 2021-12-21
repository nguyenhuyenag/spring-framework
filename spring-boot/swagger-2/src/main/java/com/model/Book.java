package com.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Book {

	@NotNull(message = "Name can't be null")
	private String name;

	@Min(value = 0, message = "Min Price can't be positive")
	private Integer price;

	public Book() {

	}

	public Book(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
