package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "file_store")
public class FileStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String fileId;
	private String fileName;
	private String fileContent;
	
	public FileStore() {
		this.fileId = RandomStringUtils.randomAlphanumeric(20).toUpperCase();
	}

}
