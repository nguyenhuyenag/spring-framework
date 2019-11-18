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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable( //
		name = "product_category", //
		joinColumns = { @JoinColumn(name = "category_id") }, //
		inverseJoinColumns = { @JoinColumn(name = "product_id") } //
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