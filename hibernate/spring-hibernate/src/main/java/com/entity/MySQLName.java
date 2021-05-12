package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*-
 *	MySQL				JPA
 *
 *	name				name
 *	Name				name
 *	NAME				name
 *
 *	my_name				myName
 *
 *	camelCase			@Column(name = "camelcase")
 *						String camelCase;
 */
@Entity
@Table(name = "mysql_name")
@SuppressWarnings("unused")
public class MySQLName implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String age;
	private String phone;

	@Column(name = "camelcase")
	private String camelCase;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "language_name")
	private String language;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@PrePersist
	public void prePersist() {
		System.out.println("pre persist!");
	}

	@PostPersist
	public void postPersist() {
		System.out.println("post persist!");
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("pre update!");
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("post update!");
	}

	@PreRemove
	public void preRemove() {
		System.out.println("pre remove!");
	}

	@PostRemove
	public void postRemove() {
		System.out.println("post remove!");
	}

}
