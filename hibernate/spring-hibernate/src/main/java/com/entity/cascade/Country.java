package com.entity.cascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

/*-
	Country country = new Country("Viet Nam");
	country.getListProvince().add(new Province(null, "Ho Noi", country));
	country.getListProvince().add(new Province(null, "Ho Chi Minh", country));
	country.getListProvince().add(new Province(null, "Hai Phong", country));
	country.getListProvince().add(new Province(null, "Vung Tau", country));

	Country country = new Country("Tây Ban Nha");
	country.getListProvince().add(new Province(null, "Barcelona", country));
	country.getListProvince().add(new Province(null, "Madrid", country));
	country.getListProvince().add(new Province(null, "Sevilla ", country));
	country.getListProvince().add(new Province(null, "Valencia", country));
*/
@Getter
@Setter
@Entity
@Table(name = "country")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Country {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany( //
		mappedBy = "country", //
		fetch = FetchType.EAGER, //
		cascade = { CascadeType.REMOVE, CascadeType.PERSIST } //
	)
	private List<Province> listProvince = new ArrayList<>();

	@Override
	public String toString() {
		return "Country [id=" + this.id + ", name=" + this.name + "]";
	}

}
