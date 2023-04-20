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

@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@Entity
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable( //
		name = "product_category",
		joinColumns = { @JoinColumn(name = "product_id") }, //
		inverseJoinColumns = { @JoinColumn(name = "category_id") } //
	)
	private Set<Category> categorys = new HashSet<>();

	public Product(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.id + ", name=" + this.name + "  - categories size: " + this.categorys.size() + "]";
	}

}