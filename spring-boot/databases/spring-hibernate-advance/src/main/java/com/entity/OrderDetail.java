package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orderdetails")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = -1338488274811870930L;

	@Id
	@Column(name = "productcode")
	private String productCode;

	@Column(name = "quantityordered")
	private int quantityOrdered;

	@Column(name = "priceeach")
	private double priceEach;

	@Column(name = "orderlinenumber")
	private int orderLineNumber;

	@ManyToOne
	@JoinColumn(name = "ordernumber")
	private Order order;

	// Add constructors, additional methods if needed

}
