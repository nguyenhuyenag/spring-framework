package com.boot.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
// @NamedQuery(name = "Countrylanguage.findAll", query = "SELECT c FROM
// Countrylanguage c")
public class Countrylanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountrylanguagePK id;

	private String isOfficial;

	private Float percentage;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "CountryCode")
	private Country country;

}