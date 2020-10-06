package com.entity.manytoone.onetomany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@SuppressWarnings("unused")
	private String name;

	// Một Company có nhiều Staff => @OneToMany đặt ở Company
	// Mapping thông qua thuộc tính `company` trong class Staff
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
	private List<Staff> listStaff;

	public Company(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
