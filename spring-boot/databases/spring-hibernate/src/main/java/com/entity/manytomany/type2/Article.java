package com.entity.manytomany.type2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

/**
 * Quan hệ N-N là quan hệ giữa hai tập thực thể trong đó một thực thể của tập
 * này có thể liên kết với 0, 1 hoặc nhiều thực thể của tập kia, và ngược lại.
 * Thường quan hệ N-N có thêm phần dữ liệu giao nhau để thêm thông tin cụ thể
 * cho mối quan hệ
 * 
 * Ví dụ: Mối quan hệ giữa bảng bài báo và bảng thẻ, một bài báo có thể có nhiều
 * thẻ, 1 thẻ có thể xuất hiện trong nhiều bài báo
 */
@Data
@Entity(name = "article")
public class Article {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	
	@ManyToMany
	@JoinTable( //
		name = "article_tag", // Tên bảng mới được sinh ra trong CSDL
		// Chỉ ra khóa ngoại ứng với khóa chính của entity là chủ sở hữu của mối quan hệ (Article)
		joinColumns = @JoinColumn(name = "article_id"), //
		// Chỉ ra khóa ngoại ứng với khóa chính của entity còn lại không phải chủ sở hữu của mối quan hệ
		inverseJoinColumns = @JoinColumn(name = "tag_id") //
	)
	private Set<Tag> tags = new HashSet<>();
	
	public Article(String title) {
		this.title = title;
	}
	
}
