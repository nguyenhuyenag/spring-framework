package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = -1368449198257785791L;

	@Id
	@Column(name = "ordernumber")
	private Integer orderNumber;

	@Column(name = "orderdate")
	private Date orderDate;

	@Column(name = "requireddate")
	private Date requiredDate;

	@Column(name = "shippeddate")
	private Date shippedDate;

	@Column(name = "status")
	private String status;

	@Column(name = "comments")
	private String comments;

	@Column(name = "customernumber")
	private int customerNumber;

	// Add constructors, additional methods if needed
}
