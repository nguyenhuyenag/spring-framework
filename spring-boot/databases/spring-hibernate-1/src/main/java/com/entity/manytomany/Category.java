package com.entity.manytomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*-
 * - Sử dụng @ManyToMany để biểu thị mối quan hệ nhiều - nhiều
 * - @JoinTable sẽ chỉ rõ bảng trung gian trong thuộc tính `name = `
 * - joinColumns sẽ chỉ rõ cột mapping với table hiện tại
 * - inverseJoinColumns sẽ chỉ rõ cột mapping với table còn lại
 */
@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@Entity
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable( //
		name = "product_category", // Tạo ra một join table tên là "product_category"
		joinColumns = { @JoinColumn(name = "category_id") }, 	  // khóa ngoại chính là category_id trỏ tới class hiện tại
		inverseJoinColumns = { @JoinColumn(name = "product_id") } // khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
	)
	private Set<Product> products = new HashSet<>();

	public Category(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + this.id + ", name=" + this.name + "  - products size: " + this.products.size() + "]";
	}

}