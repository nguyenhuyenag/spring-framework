package com.primary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerNumber")
	private Integer customerNumber;

	@Column(name = "customerName", nullable = false)
	private String customerName;

	@Column(name = "contactLastName", nullable = false)
	private String contactLastName;

	@Column(name = "contactFirstName", nullable = false)
	private String contactFirstName;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "addressLine1", nullable = false)
	private String addressLine1;

	@Column(name = "addressLine2")
	private String addressLine2;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "postalCode")
	private String postalCode;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "salesRepEmployeeNumber")
	private Integer salesRepEmployeeNumber;

	@Column(name = "creditLimit")
	private Double creditLimit;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
