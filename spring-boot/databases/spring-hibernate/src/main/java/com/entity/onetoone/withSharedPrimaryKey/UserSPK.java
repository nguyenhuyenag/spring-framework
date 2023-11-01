package com.entity.onetoone.withSharedPrimaryKey;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class UserSPK {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AddressSPK address;

}
