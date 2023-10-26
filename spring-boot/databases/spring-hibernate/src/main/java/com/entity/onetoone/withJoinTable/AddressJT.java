package com.entity.onetoone.withJoinTable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "address_jt")
public class AddressJT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String street;
	private String city;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private UserJT user;

}
