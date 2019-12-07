package com.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the countrylanguage database table.
 * 
 */
@Entity
public class Countrylanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountrylanguagePK id;

	private String isOfficial;

	private float percentage;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "CountryCode", insertable = false, updatable = false)
	private Country country;

	public Countrylanguage() {
	}

	public CountrylanguagePK getId() {
		return this.id;
	}

	public void setId(CountrylanguagePK id) {
		this.id = id;
	}

	public String getIsOfficial() {
		return this.isOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		this.isOfficial = isOfficial;
	}

	public float getPercentage() {
		return this.percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}