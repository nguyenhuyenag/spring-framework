package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "file_store")
public class FileStore {

	@Id
	private String id;
	private String fileName;
	private String fileContent;

}
