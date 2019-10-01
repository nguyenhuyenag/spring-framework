package com.boot.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
// @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
@Data
public class Country implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private Integer capital;

	private String code2;

	private String continent;

	private Float gnp;

	private Float GNPOld;

	private String governmentForm;

	private String headOfState;

	private Short indepYear;

	private Float lifeExpectancy;

	private String localName;

	private String name;

	private Integer population;

	private String region;

	private Float surfaceArea;

	// bi-directional many-to-one association to City
	@OneToMany(mappedBy = "country")
	private List<City> cities;

	// bi-directional many-to-one association to Countrylanguage
	@OneToMany(mappedBy = "country")
	private List<Countrylanguage> countrylanguages;

	public Country() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCapital() {
		return this.capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCode2() {
		return this.code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public float getGnp() {
		return this.gnp;
	}

	public void setGnp(float gnp) {
		this.gnp = gnp;
	}

	public float getGNPOld() {
		return this.GNPOld;
	}

	public void setGNPOld(float GNPOld) {
		this.GNPOld = GNPOld;
	}

	public String getGovernmentForm() {
		return this.governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}

	public String getHeadOfState() {
		return this.headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	public short getIndepYear() {
		return this.indepYear;
	}

	public void setIndepYear(short indepYear) {
		this.indepYear = indepYear;
	}

	public float getLifeExpectancy() {
		return this.lifeExpectancy;
	}

	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public String getLocalName() {
		return this.localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return this.population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public float getSurfaceArea() {
		return this.surfaceArea;
	}

	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);
		return city;
	}

	public List<Countrylanguage> getCountrylanguages() {
		return this.countrylanguages;
	}

	public void setCountrylanguages(List<Countrylanguage> countrylanguages) {
		this.countrylanguages = countrylanguages;
	}

	public Countrylanguage addCountrylanguage(Countrylanguage countrylanguage) {
		getCountrylanguages().add(countrylanguage);
		countrylanguage.setCountry(this);
		return countrylanguage;
	}

	public Countrylanguage removeCountrylanguage(Countrylanguage countrylanguage) {
		getCountrylanguages().remove(countrylanguage);
		countrylanguage.setCountry(null);
		return countrylanguage;
	}

}
