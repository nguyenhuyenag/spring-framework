package com.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "book")
public class Book {

	@Id
	// @Field("_id")
    private Integer id;

	private String title;
	private String isbn;

	@JsonProperty("pageCount")
	private Integer pageCount;

	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;

	@JsonProperty("shortDescription")
	private String shortDescription;

	@JsonProperty("longDescription")
	private String longDescription;

	@JsonProperty("status")
	private String status;

	@JsonProperty("authors")
	private List<String> authors;

	@JsonProperty("categories")
	private List<String> categories;

	@JsonProperty("publishedDate")
	private Date publishedDate;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
