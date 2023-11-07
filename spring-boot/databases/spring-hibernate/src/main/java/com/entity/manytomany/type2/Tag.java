package com.entity.manytomany.type2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity(name = "tag")
public class Tag {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	/**
	 * mappedBy = "tags": Chỉ ra tên của thuộc tính ở entity đối diện dùng để mapping cho mối quan hệ
	 * (thuộc tính của entity đối diện có tên là 'tags')
	 */
	@ManyToMany(mappedBy = "tags")
	Set<Article> articles = new HashSet<>();

	public Tag(String title) {
		this.title = title;
	}

}
