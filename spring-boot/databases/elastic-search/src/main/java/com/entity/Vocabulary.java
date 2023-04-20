package com.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "vocabulary")
public class Vocabulary {

	@Id
	private String id; // ObjectId id;

	private String word;
	private String pronounce;
	private String translate;
	private int count = 0;

	@CreatedDate
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Field(value = "created_datetime")
	private Date createdDatetime;

	@LastModifiedDate
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Field(value = "last_modified_date")
	private Date lastModifiedDate;

	public Vocabulary(String word, String pronounce, String translate) {
		this.word = word;
		this.pronounce = pronounce;
		this.translate = translate;
	}

	private boolean equal(String s1, String s2) {
		return s1.trim().equalsIgnoreCase(s2.trim());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vocabulary) {
			Vocabulary vcb = (Vocabulary) obj;
			if (equal(this.word, vcb.word) && equal(this.pronounce, vcb.pronounce)
					&& equal(this.translate, vcb.translate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.word.hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
