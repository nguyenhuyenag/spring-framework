package com.boot.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Entity
// @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
@Data
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String district;

	private String name;

	private Integer population;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "CountryCode")
	private Country country;


}
