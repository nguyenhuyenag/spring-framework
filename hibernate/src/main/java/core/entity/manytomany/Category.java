package core.entity.manytomany;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
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
	private Set<Product> products;

	public Category(String name, Product... products) {
		this.name = name;
		this.setProducts(Stream.of(products).collect(Collectors.toSet()));
	}

	@Override
	public String toString() {
		return "Category [id=" + this.id + ", name=" + this.name + "  - products size: " + this.products.size() + "]";
	}

}