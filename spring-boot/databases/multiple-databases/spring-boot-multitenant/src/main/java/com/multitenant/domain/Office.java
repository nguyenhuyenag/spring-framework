package com.multitenant.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "office")
public class Office implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "officeCode", length = 10)
	private String officeCode;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "addressLine1", nullable = false)
	private String addressLine1;

	@Column(name = "addressLine2")
	private String addressLine2;

	@Column(name = "state")
	private String state;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "postalCode", nullable = false)
	private String postalCode;

	@Column(name = "territory", nullable = false)
	private String territory;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
